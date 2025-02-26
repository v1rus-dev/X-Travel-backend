package yegor.cheprasov.xtravel

import io.ktor.server.application.*
import yegor.cheprasov.xtravel.features.auth.configureAuthRouting
import yegor.cheprasov.xtravel.features.changeRole.configureChangeRoleRouting
import yegor.cheprasov.xtravel.features.city.configureCityRouting
import yegor.cheprasov.xtravel.features.country.configureCountryRouting
import yegor.cheprasov.xtravel.features.favorites.configureFavoriteRouting
import yegor.cheprasov.xtravel.features.main.configureMainRouting
import yegor.cheprasov.xtravel.features.suggestPlace.configureSuggestPlace
import yegor.cheprasov.xtravel.features.user.configureUserRouting
import yegor.cheprasov.xtravel.features.utils.configureUtilsRouting
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
    configureFiles()
    configureUtilsRouting()

    configureMainRouting()
    configureAuthRouting()

    configureUserRouting()
    configureChangeRoleRouting()
    configureSuggestPlace()

    configureCountryRouting()
    configureFavoriteRouting()
    configureCityRouting()
}