package yegor.cheprasov.xtravel.features.country

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponseRemote(
    val countries: List<ShortCountry>
)

@Serializable
data class ShortCountry(
    val countryId: Long,
    val mainPhotoUrl: String?,
    val countryNameEn: String,
    val countryNameRu: String,
    val flagUrl: String,
    val capitalId: String,
    val capitalNameEn: String,
    val capitalNameRu: String,
)