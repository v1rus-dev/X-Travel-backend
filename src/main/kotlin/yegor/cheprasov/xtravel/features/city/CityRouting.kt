package yegor.cheprasov.xtravel.features.city

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCityRouting() {
    val controller = CityController()
    routing {
        get("/city/all") {
            controller.fetchAllCities(call)
        }
    }

    routing {
        get("/city/{city_id}/info") {
            controller.fetchCityInfo(call)
        }
    }

    routing {
        get("/city/main") {
            controller.fetchCitiesForMain(call)
        }
    }

    routing {
        get("/city/{city_id}/attractions") {

        }
    }

}