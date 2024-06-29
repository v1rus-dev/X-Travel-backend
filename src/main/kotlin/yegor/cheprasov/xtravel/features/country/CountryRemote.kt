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
) {
    companion object {
        fun fromDTO(countryDTO: ShortCountryDTO): ShortCountry =
            ShortCountry(
                countryId = countryDTO.countryId,
                mainPhotoUrl = countryDTO.mainPhotoUrl,
                countryNameEn = countryDTO.countryNameEn,
                countryNameRu = countryDTO.countryNameRu,
                flagUrl = countryDTO.flagUrl,
                capitalId = countryDTO.capitalId,
                capitalNameEn = countryDTO.capitalNameEn,
                capitalNameRu = countryDTO.capitalNameRu
            )
    }
}