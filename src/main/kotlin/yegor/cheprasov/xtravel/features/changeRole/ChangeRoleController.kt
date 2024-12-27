package yegor.cheprasov.xtravel.features.changeRole

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.changeRole.ChangeRoleRepository
import yegor.cheprasov.xtravel.security.getUserId

class ChangeRoleController : KoinComponent {

    private val changeRoleRepository: ChangeRoleRepository by inject()

    suspend fun requestUpdateRole(call: ApplicationCall) {
        val principal = call.principal<JWTPrincipal>()
        val userId = principal!!.getUserId()

        println("User ID is $userId")

        call.respond(HttpStatusCode.OK)
    }

    suspend fun getRequestsForUpdatingRole(call: ApplicationCall) {

    }
}