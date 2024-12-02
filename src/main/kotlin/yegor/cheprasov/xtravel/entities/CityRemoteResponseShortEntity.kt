package yegor.cheprasov.xtravel.entities

import kotlinx.serialization.Serializable

@Serializable
data class CityRemoteResponseShortEntity(
    val cityId: String,
    val nameEn: String,
    val nameRu: String,
    val previewURL: String
)
