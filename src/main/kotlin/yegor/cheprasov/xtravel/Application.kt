package yegor.cheprasov.xtravel

import io.ktor.server.application.*
import yegor.cheprasov.xtravel.data.database.configureDatabase
import yegor.cheprasov.xtravel.features.login.configureLoginRouting
import yegor.cheprasov.xtravel.features.register.configureRegisterRouting
import yegor.cheprasov.xtravel.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabase()
    configureHTTP()
    configureSecurity()
    configureAdministration()
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureRouting()

    configureRegisterRouting()
    configureLoginRouting()
}
