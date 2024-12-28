package yegor.cheprasov.xtravel.features.auth.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val accessToken: String,
    val refreshToken: String
)
