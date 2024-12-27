package yegor.cheprasov.xtravel.features.user

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureUserRouting() {
    val userController = UserController()
    routing {
        authenticate("auth-jwt") {
            get("/user/info") {
                userController.getUserInformation(call)
            }
        }
    }
}