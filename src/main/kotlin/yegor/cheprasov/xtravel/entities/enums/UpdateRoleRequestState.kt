package yegor.cheprasov.xtravel.entities.enums

import io.ktor.server.plugins.*

enum class UpdateRoleRequestState(val id: String) {
    Pending("pending"),
    Approved("approved"),
    Rejected("rejected"),
    Cancelled("cancelled"),
    RequiresAdditionalInformation("additionalInformation");

    companion object {
        fun getById(id: String) =
            entries.firstOrNull { it.id == id } ?: throw NotFoundException("$id not found in UpdateRoleRequestState")
    }
}