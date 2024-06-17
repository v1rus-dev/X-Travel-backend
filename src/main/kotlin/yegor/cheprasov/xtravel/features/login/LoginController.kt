package yegor.cheprasov.xtravel.features.login

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class LoginController(
    private val call: ApplicationCall
) {

    suspend fun performLogin() {
//        val loginReceiveRemote = call.receive(LoginReceiveRemote::class)

        call.respond("Success login")
    }

}