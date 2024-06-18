package yegor.cheprasov.xtravel.data.database.users

import java.util.UUID

data class UserDTO(
    val userId: UUID,
    val login: String,
    val passwordHash: String,
    val email: String,
    val createdAt: Long,
    val updatedAt: Long
)