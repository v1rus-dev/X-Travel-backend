package yegor.cheprasov.xtravel.features.user

import kotlinx.serialization.Serializable

@Serializable
class UserInfoResponse(
    val userId: String,
    val name: String,
    val email: String,
    val role: String
)