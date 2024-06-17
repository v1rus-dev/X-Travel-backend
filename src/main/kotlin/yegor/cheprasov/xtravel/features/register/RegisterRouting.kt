package yegor.cheprasov.xtravel.features.register

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {

            call.respond("Successfully register")
//            val registerController = RegisterController(call)
//            registerController.registerNewUser()
        }
    }

}