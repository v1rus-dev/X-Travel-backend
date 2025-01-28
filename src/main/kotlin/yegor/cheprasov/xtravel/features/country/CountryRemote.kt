package yegor.cheprasov.xtravel.features.country

import kotlinx.serialization.Serializable
import yegor.cheprasov.xtravel.features.main.ShortCity

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
    val list: List<ShortCity>
)

@Serializable
data class CountryAttractionsResponseRemote(
    val list: List<String>
)