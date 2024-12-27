package yegor.cheprasov.xtravel.features.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.features.user.mapper.UserMapper

class UserController : KoinComponent {

    private val userRepository: UserRepository by inject()

    suspend fun getUserInformation(call: ApplicationCall) {
        val principal = call.principal<JWTPrincipal>()
        val login = principal?.payload?.getClaim("login")?.asString()
        val userId = principal?.payload?.getClaim("userId")?.asString()

        if (login == null || userId == null) {
            call.respond(HttpStatusCode.Forbidden, "You are not logged in")
            return
        }

        val user = userRepository.fetchUser(login).await()

        if (user == null) {
            call.respond(HttpStatusCode.Forbidden, "You are not logged in or user not found")
            return
        }

        call.respond(HttpStatusCode.OK, UserMapper.mapToUserInfo(user))
    }

    suspend fun requestUpdateRole(call: ApplicationCall) {

    }

    suspend fun getRequestsForUpdatingRole(call: ApplicationCall) {

    }

}