package yegor.cheprasov.xtravel.features.suggestPlace

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository

class SuggestPlaceController : KoinComponent {

    private val userRepository: UserRepository by inject()

    suspend fun suggestPlace(call: ApplicationCall) {
        val userId = call.request.queryParameters["user_id"]

        if (userId == null) {
            call.respond(HttpStatusCode.BadRequest, "Missing queryParameters")
            return
        }

        val user = userRepository.fetchUserById(userId)

        println("User $user")
    }
}