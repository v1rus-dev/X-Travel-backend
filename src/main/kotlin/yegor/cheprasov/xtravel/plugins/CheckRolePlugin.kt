package yegor.cheprasov.xtravel.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

/**
 * Example
 *
 * fun Application.configureUserRouting() {
 *     val userController = UserController()
 *     routing {
 *     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *         install(RoleValidatingPlugin) {
 *              roles = setOf(UserRole.Default.id)
 *         }
 *     !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *         authenticate("auth-jwt") {
 *             get("/user/info") {
 *                 userController.getUserInformation(call)
 *             }
 *         }
 *     }
 * }
 *
 *  * fun Application.configureUserRouting() {
 *  *     val userController = UserController()
 *  *     routing {
 *                  authenticate("auth-jwt") {
 *             route("/send-role-change-request") {
 *                 install(RoleValidatingPlugin) {
 *                     roles = setOf(UserRole.Default.id)
 *                 }
 *                 put {
 *                     controller.requestUpdateRole(call)
 *                 }
 *             }
 *             }
 *  *     }
 *  * }
 */

val RoleValidatingPlugin = createRouteScopedPlugin(
    name = "AuthorizationPlugin",
    createConfiguration = ::PluginConfiguration
) {
    val roles = pluginConfig.roles
    pluginConfig.apply {
        on(AuthenticationChecked) { call ->
            val userName = call.principal<UserIdPrincipal>()?.name
            val jwtPrincipal = call.principal<JWTPrincipal>()
            val role = jwtPrincipal?.payload?.getClaim("role")?.asString()

            if (role == null || role !in roles) {
                call.respond(HttpStatusCode.Forbidden, "Access denied!")
            }
        }
    }
}

class PluginConfiguration {
    var roles: Set<String> = emptySet()
}