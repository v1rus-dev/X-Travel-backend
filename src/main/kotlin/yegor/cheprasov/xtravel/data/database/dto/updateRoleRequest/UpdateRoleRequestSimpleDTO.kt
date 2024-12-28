package yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest

import yegor.cheprasov.xtravel.entities.enums.UpdateRoleRequestState
import java.util.UUID

data class UpdateRoleRequestSimpleDTO(
    val requestId: UUID,
    val userId: UUID,
    val confirmationCount: Int,
    val state: UpdateRoleRequestState
)
