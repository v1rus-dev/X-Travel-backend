package yegor.cheprasov.xtravel.features.auth

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAuthRouting() {

    val authController = AuthController()

    routing {
        post("/auth/login") {
            authController.performLogin(call)
        }

        post("/auth/register") {
            authController.performRegister(call)
        }

        post("/auth/refresh") {
            authController.performRefreshToken(call)
        }
    }
}