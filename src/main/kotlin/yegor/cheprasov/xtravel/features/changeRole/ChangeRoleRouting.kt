package yegor.cheprasov.xtravel.features.changeRole

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureChangeRoleRouting() {
    val controller = ChangeRoleController()

    routing {
        authenticate("auth-jwt") {
            put("/send-role-change-request") {
                controller.requestUpdateRole(call)
            }

            get("/get-role-change-requests") {
                controller.getRequestsForUpdatingRole(call)
            }
        }
    }
}