package yegor.cheprasov.xtravel.features.changeRole

import kotlinx.serialization.Serializable

@Serializable
data class ChangeRoleReceiveRemote(
    val description: String,
    val instagramLink: String?,
    val facebookLink: String?,
    val xLink: String?
)

@Serializable
data class ChangeRoleResponseRemote(
    val requestId: String,
)