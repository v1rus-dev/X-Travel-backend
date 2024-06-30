package yegor.cheprasov.xtravel.features.city

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCityRouting() {

    routing {
        get("/allCities") {
            val controller = CityController(call)
            controller.getAllCities()
        }
    }

}