package yegor.cheprasov.xtravel.mappers

import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO
import yegor.cheprasov.xtravel.entities.CountryRemoteResponseShortEntity
import yegor.cheprasov.xtravel.features.country.ShortCountry

object CountryMapper {
    fun mapShortDTOtoNetwork(shortCountryDTO: ShortCountryDTO): ShortCountry =
        ShortCountry(
            countryId = shortCountryDTO.countryId,
            mainPhotoUrl = getMainPhotoURL(shortCountryDTO.mainFolderName),
            countryNameEn = shortCountryDTO.countryNameEn,
            countryNameRu = shortCountryDTO.countryNameRu,
            flagUrl = shortCountryDTO.flagUrl ?: getFlagUrl(shortCountryDTO.mainFolderName),
            capitalId = shortCountryDTO.capitalId,
            capitalNameEn = shortCountryDTO.capitalNameEn,
            capitalNameRu = shortCountryDTO.capitalNameRu
        )

    fun mapShortDTOtoNetworkMain(shortCountryDTO: ShortCountryDTO): CountryRemoteResponseShortEntity =
        CountryRemoteResponseShortEntity(
            countryId = shortCountryDTO.countryId,
            mainPhotoUrl = getMainPhotoURL(shortCountryDTO.mainFolderName),
            countryNameEn = shortCountryDTO.countryNameEn,
            countryNameRu = shortCountryDTO.countryNameRu,
            flagUrl = shortCountryDTO.flagUrl ?: getFlagUrl(shortCountryDTO.mainFolderName),
            capitalId = shortCountryDTO.capitalId,
            capitalNameEn = shortCountryDTO.capitalNameEn,
            capitalNameRu = shortCountryDTO.capitalNameRu
        )

    private fun getMainPhotoURL(mainFolderName: String): String =
        "resources/countries/$mainFolderName/images/main.jpg"

    private fun getFlagUrl(mainFolderName: String): String =
        "resources/countries/$mainFolderName/images/flag.jpg"
}