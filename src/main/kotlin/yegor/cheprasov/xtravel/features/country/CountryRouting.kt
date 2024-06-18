package yegor.cheprasov.xtravel.features.country

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCountryRouting() {

    routing {
        get("/countries") {
            val countryController = CountryController(call)
            countryController.getCountries()
        }
    }

}