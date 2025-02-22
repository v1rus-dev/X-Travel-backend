package yegor.cheprasov.xtravel.features.city

import kotlinx.serialization.Serializable

@Serializable
data class CityInfo(
    val cityId: Long,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val countryId: Long,
    val countryName: String,
    val mainImage: String,
    val photos: List<String>,
)