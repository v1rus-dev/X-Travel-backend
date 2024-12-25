package yegor.cheprasov.xtravel.features.register

import at.favre.lib.crypto.bcrypt.BCrypt
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.Deferred
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.security.JwtConfig
import java.util.*

class RegisterController(
    private val call: ApplicationCall
) : KoinComponent {

    private val jwtConfig: JwtConfig by inject()
    private val userRepository: UserRepository by inject()

    suspend fun registerNewUser() {
        println("Register new user")
        val registerReceiveRemote = call.receive(RegisterReceiveRemote::class)
        println(registerReceiveRemote.toString())
        val userDTO: UserDTO? = userRepository.fetchUser(registerReceiveRemote.login).await()
        println("User from database: $userDTO")

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val user = UserDTO(
                userId = UUID.randomUUID(),
                login = registerReceiveRemote.login,
                passwordHash = BCrypt.withDefaults().hashToString(12, registerReceiveRemote.password.toCharArray()),
                email = registerReceiveRemote.email,
                name = registerReceiveRemote.name,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            userRepository.insert(user)
            val token = jwtConfig.makeToken(user.login)
            call.respond(RegisterResponseRemote(token = token))
        }
    }

}