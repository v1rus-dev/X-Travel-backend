package yegor.cheprasov.xtravel.features.login

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.security.JwtConfig

class LoginController(
    private val call: ApplicationCall
) : KoinComponent {

    private val jwtConfig: JwtConfig by inject()
    private val userRepository: UserRepository by inject()

    suspend fun performLogin() {
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
            val token = jwtConfig.makeToken(login = user.email)
            call.respond(HttpStatusCode.OK, LoginResponseRemote(token, name = userDTO.name, email = userDTO.email))
        }
    }

}