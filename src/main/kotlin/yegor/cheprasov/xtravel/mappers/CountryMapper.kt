package yegor.cheprasov.xtravel.mappers

import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO
import yegor.cheprasov.xtravel.features.country.ShortCountry

object CountryMapper {
    fun mapShortDTOtoNetwork(shortCountryDTO: ShortCountryDTO): ShortCountry =
        ShortCountry(
            countryId = shortCountryDTO.countryId,
            mainPhotoUrl = shortCountryDTO.mainPhotoUrl ?: getMainPhotoURL(shortCountryDTO.mainFolderName),
            countryNameEn = shortCountryDTO.countryNameEn,
            countryNameRu = shortCountryDTO.countryNameRu,
            flagUrl = shortCountryDTO.flagUrl,
            capitalId = shortCountryDTO.capitalId,
            capitalNameEn = shortCountryDTO.capitalNameEn,
            capitalNameRu = shortCountryDTO.capitalNameRu
        )

    private fun getMainPhotoURL(mainFolderName: String): String =
        "files/counties/$mainFolderName/main.jpg"
}