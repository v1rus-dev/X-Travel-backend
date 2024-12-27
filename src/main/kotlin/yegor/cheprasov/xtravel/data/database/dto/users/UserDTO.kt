package yegor.cheprasov.xtravel.data.database.dto.users

import yegor.cheprasov.xtravel.entities.users.UserRole
import java.util.UUID

data class UserDTO(
    val userId: UUID,
    val login: String,
    val passwordHash: String,
    val email: String,
    val name: String,
    val createdAt: Long,
    val updatedAt: Long,
    val role: UserRole
)