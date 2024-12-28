package yegor.cheprasov.xtravel.features.changeRole

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest.CreateRoleRequestDTO
import yegor.cheprasov.xtravel.data.repositories.changeRole.ChangeRoleRepository
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.security.getUserId

class ChangeRoleController : KoinComponent {

    private val userRepository: UserRepository by inject()
    private val changeRoleRepository: ChangeRoleRepository by inject()

    suspend fun requestUpdateRole(call: ApplicationCall) {
        val principal = call.principal<JWTPrincipal>()
        val userId = principal!!.getUserId()

        val receive = call.receive<ChangeRoleReceiveRemote>()

        if (!checkWebLinksCount(receive)) {
            return call.respond(HttpStatusCode.BadRequest, "Invalid Web links count")
        }

        if (!checkDescription(receive)) {
            return call.respond(HttpStatusCode.BadRequest, "Invalid Description")
        }

        val currentRequest = changeRoleRepository.getSimpleChangeRoleRequestInfoByUserId(userId).await()

        if (currentRequest != null) {
            return call.respond(HttpStatusCode.Conflict, "Request already exists")
        }

        val user = userRepository.fetchUserById(userId).await() ?: return call.respond(
            HttpStatusCode.NotFound,
            "User does not exist"
        )

        val requestId = changeRoleRepository.createChangeRoleRequest(
            createRoleRequestDTO = CreateRoleRequestDTO(
                userId = user.userId.toString(),
                description = receive.description,
                instagramLink = receive.instagramLink,
                facebookLink = receive.facebookLink,
                xLink = receive.xLink,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        ).await()

        call.respond(HttpStatusCode.OK, ChangeRoleResponseRemote(requestId = requestId))
    }

    suspend fun getRequestsForUpdatingRole(call: ApplicationCall) {

    }

    private fun checkWebLinksCount(
        request: ChangeRoleReceiveRemote
    ): Boolean {
        val urlRegex = "^(https?|ftp)://[\\w-]+(\\.[\\w-]+)+[/#?]?.*$".toRegex()

        fun isValidUrl(url: String?): Boolean {
            return !url.isNullOrEmpty() && urlRegex.matches(url)
        }

        return isValidUrl(request.instagramLink) ||
                isValidUrl(request.facebookLink) ||
                isValidUrl(request.xLink)
    }

    private fun checkDescription(
        request: ChangeRoleReceiveRemote
    ): Boolean = request.description.trim().isNotEmpty()
}