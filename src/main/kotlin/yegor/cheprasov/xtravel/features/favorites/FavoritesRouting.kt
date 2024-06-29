package yegor.cheprasov.xtravel.features.favorites

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureFavoriteRouting() {
    routing {
        authenticate("auth-jwt", strategy = AuthenticationStrategy.Required) {
            get("/favorites") {
                val favoritesController = FavoritesController(call)
                favoritesController.getFavorites()
            }
        }
    }
}