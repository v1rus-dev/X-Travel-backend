package yegor.cheprasov.xtravel.features.login

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
        // Check username and password

        val userDTO = userRepository.fetchUser(user.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.Conflict, "User not exist")
            return
        }

        if (user.password != userDTO.passwordHash) {
            call.respond(HttpStatusCode.Conflict, "Password is not correct")
            return
        }

        val token = jwtConfig.makeToken(login = user.login)
        call.respond(LoginResponseRemote(token = token))
    }

}