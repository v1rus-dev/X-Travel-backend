package yegor.cheprasov.xtravel.data.database.usersProfiles

import java.util.UUID

data class UserProfileDTO(
    val profileId: Int,
    val userId: UUID,
    val firstName: String,
    val lastName: String
)
