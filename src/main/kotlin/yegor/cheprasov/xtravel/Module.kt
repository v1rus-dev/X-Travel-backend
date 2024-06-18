package yegor.cheprasov.xtravel

import io.ktor.server.application.*
import yegor.cheprasov.xtravel.features.country.configureCountryRouting
import yegor.cheprasov.xtravel.features.login.configureLoginRouting
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

    configureRegisterRouting()
    configureLoginRouting()
    configureCountryRouting()
}