package yegor.cheprasov.xtravel.features.city

import kotlinx.serialization.Serializable

@Serializable
data class GetAllCitiesResponseRemote(
    val cities: List<CityResponse>
)

@Serializable
data class CityResponse(
    val cityId: String,
    val nameEn: String,
    val nameRu: String,
    val descriptionEn: String,
    val descriptionRu: String,
    val imagesUrls: List<String>,
    val population: Int,
    val latitude: Double,
    val longitude: Double,
)