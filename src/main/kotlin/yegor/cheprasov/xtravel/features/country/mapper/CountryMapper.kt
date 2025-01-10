package yegor.cheprasov.xtravel.features.country.mapper

import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO
import yegor.cheprasov.xtravel.features.country.ShortCountry
import yegor.cheprasov.xtravel.utils.FileService

object CountryMapper {
    fun mapToShort(dto: ShortCountryDTO, fileService: FileService, currentAddress: String): ShortCountry {
        val listAllFiles = fileService.listAllFiles("countries/${dto.folderName}/images").filter { !it.contains("flag.png") }
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
}