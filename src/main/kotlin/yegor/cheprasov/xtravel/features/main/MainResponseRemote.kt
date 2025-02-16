package yegor.cheprasov.xtravel.features.main

import kotlinx.serialization.Serializable
import yegor.cheprasov.xtravel.features.country.ShortCountry

@Serializable
data class MainCountriesResponseRemote(
    val list: List<ShortCountry>
)

@Serializable
data class MainCityResponseRemote(
    val list: List<ShortCity>
)

@Serializable
data class MainAttractionResponseRemote(
    val list: List<ShortAttraction>
)


@Serializable
data class ShortCity(
    val cityId: Long,
    val countryId: Long,
    val countryName: String,
    val cityName: String,
    val imageUrl: String
)

@Serializable
data class ShortCityCapital(
    val cityId: Long,
    val countryId: Long,
    val cityName: String,
    val imageUrl: String
)

@Serializable
data class ShortAttraction(
    val attractionId: Int,
    val name: String,
    val imageUrl: String,
    val folderName: String
)