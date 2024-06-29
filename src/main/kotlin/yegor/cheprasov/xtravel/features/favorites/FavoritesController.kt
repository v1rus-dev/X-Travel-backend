package yegor.cheprasov.xtravel.features.favorites

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

class FavoritesController(
    private val call: ApplicationCall
) {

    suspend fun getFavorites() {
        val principal = call.principal<JWTPrincipal>()
        val listOfFavorites = listOf("Favorite1", "Favorite2", "Favorite3", "Favorite4")
        call.respond(listOfFavorites)
    }

    suspend fun addToFavorites() {

    }

}