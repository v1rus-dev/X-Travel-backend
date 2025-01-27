package yegor.cheprasov.xtravel.features.city

import kotlinx.serialization.Serializable

@Serializable
data class CityShortResponseRemote(
    val list: List<ShortCity>
)

@Serializable
data class ShortCity(
    val cityId: Long,
    val cityName: String,
    val folderName: String,
)