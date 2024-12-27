package yegor.cheprasov.xtravel.features.suggestPlace

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureSuggestPlace() {

    val controller : SuggestPlaceController = SuggestPlaceController()

    routing {
        authenticate("auth-jwt") {
            put("/suggestPlace") {
                controller.suggestPlace(call)
            }
        }
    }

}