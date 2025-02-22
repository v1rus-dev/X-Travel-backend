package yegor.cheprasov.xtravel.features.admin

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import yegor.cheprasov.xtravel.entities.enums.UserRole
import yegor.cheprasov.xtravel.plugins.RoleValidatingPlugin

fun Application.configureAdminRouting() {
    val controller = AdminController()
    routing {
        authenticate("auth-jwt") {
            install(RoleValidatingPlugin) {
                roles = setOf(UserRole.Admin.id)
            }
            get("/admin/city/all") {
                controller.getAllCities(call)
            }

            get("/admin/country/all") {
                controller.getAllCountries(call)
            }

            get("/admin/attraction/all") {

                controller.getAllAttractions(call)
            }

            put("/admin/city/update") {
                controller.updateCity(call)
            }

            put("/admin/country/update") {
                controller.updateCountry(call)
            }

            put("/admin/attraction/update") {
                controller.updateAttraction(call)
            }
        }
    }
}