package yegor.cheprasov.xtravel.features.country

import kotlinx.serialization.Serializable
import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO

@Serializable
data class CountryResponseRemote(
    val countries: List<ShortCountry>
)

@Serializable
data class ShortCountry(
    val countryId: Int,
    val mainPhotoUrl: String?,
    val countryNameEn: String,
    val countryNameRu: String,
    val flagUrl: String,
    val capitalId: String,
    val capitalNameEn: String,
    val capitalNameRu: String,
)