package yegor.cheprasov.xtravel.features.auth.remote

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenReceiveRemote(
    val refreshToken: String
)

@Serializable
data class RefreshTokenResponseRemote(
    val accessToken: String,
    val refreshToken: String
)