package yegor.cheprasov.xtravel.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.users.UserDTO
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.security.JwtConfig
import java.util.*

class RegisterController(
    private val call: ApplicationCall
) : KoinComponent {

    private val jwtConfig: JwtConfig by inject()
    private val userRepository: UserRepository by inject()

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive(RegisterReceiveRemote::class)
        val userDTO = userRepository.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val user = UserDTO(
                userId = UUID.randomUUID(),
                login = registerReceiveRemote.login,
                passwordHash = registerReceiveRemote.password,
                email = registerReceiveRemote.email,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            userRepository.insert(user)
            val token = jwtConfig.makeToken(user.login)
            call.respond(RegisterResponseRemote(token = token))
        }
    }

}