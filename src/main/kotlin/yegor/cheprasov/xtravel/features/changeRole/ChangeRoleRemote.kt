package yegor.cheprasov.xtravel.features.changeRole

import kotlinx.serialization.Serializable

@Serializable
data class ChangeRoleRequest(
    val description: String,
    val instagramLink: String?,
    val facebookLink: String?,
    val xLink: String?
)