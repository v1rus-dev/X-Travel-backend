package yegor.cheprasov.xtravel

import io.ktor.server.application.*
import yegor.cheprasov.xtravel.features.city.configureCityRouting
import yegor.cheprasov.xtravel.features.country.configureCountryRouting
import yegor.cheprasov.xtravel.features.favorites.configureFavoriteRouting
import yegor.cheprasov.xtravel.features.login.configureLoginRouting
import yegor.cheprasov.xtravel.features.main.configureMainRouting
import yegor.cheprasov.xtravel.features.register.configureRegisterRouting
import yegor.cheprasov.xtravel.plugins.*

fun Application.module() {
    configureHTTP()
    configureSecurity()
    configureJWT()
    configureAdministration()
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureRouting()

    configureMainRouting()
    configureRegisterRouting()
    configureLoginRouting()
    configureCountryRouting()
    configureFavoriteRouting()
    configureCityRouting()
}