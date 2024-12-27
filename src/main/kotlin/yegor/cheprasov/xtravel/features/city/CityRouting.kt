package yegor.cheprasov.xtravel.features.city

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCityRouting() {

    routing {
        get("/city/all") {
            val controller = CityController(call)
            controller.getAllCities()
        }
    }

    routing {
        get("/city/{city_id}/info") {
            val controller = CityController(call)
            controller.getCityInfo()
        }
    }

    routing {
        get("/city/{city_id}/attractions") {
            val controller = CityController(call)
            controller.getCityAttractions()
        }
    }

}