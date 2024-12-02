package yegor.cheprasov.xtravel.features.country

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureCountryRouting() {

    routing {
        get("/countries") {
            val countryController = CountryController(call)
            countryController.getCountries()
        }
    }

}