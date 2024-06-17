package yegor.cheprasov.xtravel.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val login: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)