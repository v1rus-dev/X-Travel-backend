package yegor.cheprasov.xtravel.entities

import kotlinx.serialization.Serializable

@Serializable
data class CityRemoteResponseShortEntity(
    val cityId: Long,
    val nameEn: String,
    val nameRu: String,
    val previewURL: String
)
