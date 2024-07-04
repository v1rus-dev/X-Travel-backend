package yegor.cheprasov.xtravel.entities

import kotlinx.serialization.Serializable

@Serializable
data class CountryRemoteResponseShortEntity(
    val countryId: Int,
    val mainPhotoUrl: String? = null,
    val countryNameEn: String,
    val countryNameRu: String,
    val flagUrl: String?,
    val capitalId: String,
    val capitalNameEn: String,
    val capitalNameRu: String,
)
