package yegor.cheprasov.xtravel.features.country

import kotlinx.serialization.Serializable
import yegor.cheprasov.xtravel.features.main.ShortCity
import yegor.cheprasov.xtravel.features.main.ShortCityCapital

@Serializable
data class CountryInfoRequestRemote(
    val countryId: Long
)

@Serializable
data class CountryResponseRemote(
    val countries: List<ShortCountry>
)

@Serializable
data class ShortCountry(
    val countryId: Long,
    val countryName: String,
    val folderName: String,
    val shortName: String? = null,
    val imageUrl: String
)

@Serializable
data class CountryInfoResponseRemote(
    val countryId: Long,
    val name: String,
    val description: String,
    val shortName: String,
    val phoneCode: Int,
    val isoCode: String,
    val areaKm2: Double,
    val currencyCode: String,
    val currencyFontCode: String,
    val mainImage: String,
    val images: List<String>
)

@Serializable
data class CountryCitiesResponseRemote(
    val capital: ShortCityCapital?,
    val list: List<ShortCity>
)

@Serializable
data class CountryAttractionsResponseRemote(
    val list: List<ShortAttraction>
)

@Serializable
data class ShortAttraction(
    val attractionId: Long,
    val name: String,
    val imageUrl: String,
    val cityId: Long?,
    val cityName: String?
)