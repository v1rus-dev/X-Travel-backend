package yegor.cheprasov.xtravel.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import yegor.cheprasov.xtravel.data.database.tokens.TokenDTO
import yegor.cheprasov.xtravel.data.database.tokens.Tokens
import yegor.cheprasov.xtravel.data.database.users.UserDTO
import yegor.cheprasov.xtravel.data.database.users.Users
import java.util.UUID

class RegisterController(
    private val call: ApplicationCall
) {

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive(RegisterReceiveRemote::class)
        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            Users.insert(
                UserDTO(
                    login = registerReceiveRemote.login,
                    password = registerReceiveRemote.password,
                    firstName = registerReceiveRemote.firstName,
                    lastName = registerReceiveRemote.lastName,
                    email = registerReceiveRemote.email
                )
            )

            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )

            call.respond(RegisterResponseRemote(token = token))
        }
    }

}