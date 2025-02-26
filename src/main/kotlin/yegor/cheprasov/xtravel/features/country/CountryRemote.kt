package yegor.cheprasov.xtravel.features.country

import kotlinx.serialization.Serializable

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