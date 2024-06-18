package yegor.cheprasov.xtravel

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import yegor.cheprasov.xtravel.data.database.configureDatabase
import yegor.cheprasov.xtravel.di.databaseModule
import yegor.cheprasov.xtravel.di.environmentModule
import yegor.cheprasov.xtravel.di.jwtConfigModule

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.initialize() {

    val database = configureDatabase()

    install(Koin) {
        modules(
            environmentModule(environment),
            databaseModule(database),
            jwtConfigModule
        )
    }
    main()
}

fun Application.main() {
    module()
}