package yegor.cheprasov.xtravel.features.country.mapper

import yegor.cheprasov.xtravel.data.database.dto.attractions.ShortAttractionDTO
import yegor.cheprasov.xtravel.data.database.dto.country.CountryInfoDTO
import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO
import yegor.cheprasov.xtravel.features.country.CountryInfoResponseRemote
import yegor.cheprasov.xtravel.features.country.ShortAttraction
import yegor.cheprasov.xtravel.features.country.ShortCountry
import yegor.cheprasov.xtravel.utils.FileService

object CountryMapper {
    fun mapToShort(dto: ShortCountryDTO, fileService: FileService, currentAddress: String): ShortCountry {
        val listAllFiles =
            fileService.listAllFiles("countries/${dto.folderName}/images").filter { !it.contains("flag.png") }
        val mainImage = listAllFiles.firstOrNull { it.contains("main.jpg") } ?: listAllFiles.firstOrNull()
        return ShortCountry(
            countryId = dto.countryId,
            countryName = dto.countryName,
            folderName = dto.folderName,
            shortName = dto.shortName,
            imageUrl = if (mainImage != null) {
                "$currentAddress/resources/$mainImage"
            } else {
                ""
            }
        )
    }

    fun mapToCountryInfo(
        dto: CountryInfoDTO,
        fileService: FileService,
        currentAddress: String
    ): CountryInfoResponseRemote {
        val listAllFiles = fileService.listAllFiles("countries/${dto.folderName}/images")
        val mainImage = listAllFiles.firstOrNull { it.contains("main") } ?: listAllFiles.firstOrNull()

        val sortedImages = listAllFiles.sortedBy { it.contains("main") }

        return CountryInfoResponseRemote(
            countryId = dto.countryId,
            name = dto.name,
            description = dto.description,
            shortName = dto.shortName,
            phoneCode = dto.phoneCode,
            isoCode = dto.isoCode,
            areaKm2 = dto.areaKm2,
            currencyCode = dto.currencyCode,
            currencyFontCode = dto.currencyFontCode,
            mainImage = if (mainImage != null) {
                "$currentAddress/resources/$mainImage"
            } else {
                ""
            },
            images = sortedImages.map { "$currentAddress/resources/$it" }
        )
    }

}