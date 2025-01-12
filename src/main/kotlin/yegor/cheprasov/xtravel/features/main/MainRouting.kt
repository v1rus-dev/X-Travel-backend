package yegor.cheprasov.xtravel.features.main

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureMainRouting() {
    val mainController = MainController()

    routing {
        get("/main/countries") {
            mainController.fetchCountries(call)
        }

        get("/main/cities") {
            mainController.fetchCities(call)
        }

        get("/main/attractions") {
            mainController.fetchAttractions(call)
        }
    }

}