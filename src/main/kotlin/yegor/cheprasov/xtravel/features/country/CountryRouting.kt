package yegor.cheprasov.xtravel.features.country

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCountryRouting() {
    val controller = CountryController()
    routing {
        get("/country/all") {
            controller.getCountries(call)
        }

        get("/country/{country_id/info") {
            controller.getCountryInfo(call)
        }

        get("/country/{country_id}/cities") {
            controller.getCitiesForCountry(call)
        }

        get("/country/{country_id}/attractions") {
            controller.getAttractionsForCountry(call)
        }
    }
}