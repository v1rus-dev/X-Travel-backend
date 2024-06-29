package yegor.cheprasov.xtravel.features.main

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureMainRouting() {

    routing {
        get("/main") {
            val mainController = MainController(call)
            mainController.getMain()
        }
    }

}