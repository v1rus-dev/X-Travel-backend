package yegor.cheprasov.xtravel.features.auth

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.exceptions.JWTVerificationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.entities.enums.UserRole
import yegor.cheprasov.xtravel.features.auth.remote.*
import yegor.cheprasov.xtravel.security.JwtConfig
import java.util.*

class AuthController : KoinComponent {
    private val jwtConfig: JwtConfig by inject()
    private val userRepository: UserRepository by inject()

    suspend fun performLogin(call: ApplicationCall) {
        val user = call.receive<LoginReceiveRemote>()

        val userDTO = userRepository.fetchUser(user.email).await()

        if (userDTO == null) {
            call.respond(HttpStatusCode.Conflict, "User not exist")
            return
        }

        val result = BCrypt.verifyer().verify(user.password.toCharArray(), userDTO.passwordHash)

        if (!result.verified) {
            call.respond(HttpStatusCode.Unauthorized, "User not exist")
        } else {
            val accessToken = jwtConfig.makeAccessToken(
                login = user.email,
                role = userDTO.role.id,
                userId = userDTO.userId.toString()
            )
            val refreshToken = jwtConfig.makeRefreshToken(userDTO.userId.toString())
            call.respond(
                HttpStatusCode.OK, LoginResponseRemote(
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )
            )
        }
    }

    suspend fun performRegister(call: ApplicationCall) {
        val registerReceiveRemote = call.receive(RegisterReceiveRemote::class)
        println(registerReceiveRemote.toString())
        val userDTO: UserDTO? = userRepository.fetchUser(registerReceiveRemote.login).await()

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val userId = UUID.randomUUID()
            val user = UserDTO(
                userId = UUID.randomUUID(),
                login = registerReceiveRemote.login,
                passwordHash = BCrypt.withDefaults().hashToString(12, registerReceiveRemote.password.toCharArray()),
                email = registerReceiveRemote.email,
                name = registerReceiveRemote.name,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                role = UserRole.Default
            )

            userRepository.insert(user)
            val token = jwtConfig.makeAccessToken(user.login, role = UserRole.Default.id, userId = userId.toString())
            val refreshToken = jwtConfig.makeRefreshToken(userId = userId.toString())
            call.respond(RegisterResponseRemote(token = token, refreshToken = refreshToken))
        }
    }

    suspend fun performRefreshToken(call: ApplicationCall) {
        val refreshToken = call.receiveNullable<RefreshTokenReceiveRemote>()?.refreshToken
            ?: return call.respond(HttpStatusCode.BadRequest, "Missing refresh token")

        try {
            val decodedJWT = jwtConfig.refreshTokenVerifier.verify(refreshToken)
            val userId = decodedJWT.getClaim("userId").asString()

            val user = userRepository.fetchUserById(userId).await() ?: return call.respond(
                HttpStatusCode.BadRequest,
                "User not found"
            )

            val newAccessToken = jwtConfig.makeAccessToken(login = user.login, role = user.role.id, userId = userId)
            val newRefreshToken = jwtConfig.makeRefreshToken(userId = userId)

            call.respond(
                RefreshTokenResponseRemote(
                    accessToken = newAccessToken,
                    refreshToken = newRefreshToken
                )
            )

        } catch (e: JWTVerificationException) {
            call.respond(HttpStatusCode.Unauthorized, "Invalid refresh token")
        }

    }
}