package yegor.cheprasov.xtravel.features.mappers

import yegor.cheprasov.xtravel.data.database.dto.attractions.ShortAttractionDTO
import yegor.cheprasov.xtravel.features.country.ShortAttraction
import yegor.cheprasov.xtravel.utils.FileService

object AttractionMapper {
    fun mapToShortAttraction(dto: ShortAttractionDTO, fileService: FileService, currentAddress: String): ShortAttraction {
        val listAllFiles = fileService.listAllFiles("countries/${dto.countryFolderName}/attractions/${dto.folderName}")
        val mainImage = listAllFiles.firstOrNull { it.contains("main") } ?: listAllFiles.firstOrNull()

        return ShortAttraction(
            attractionId = dto.attractionId,
            name = dto.name,
            imageUrl = if (mainImage != null) {
                "$currentAddress/resources/$mainImage"
            } else {
                ""
            },
            cityId = dto.cityId,
            cityName = dto.cityName,
        )
    }
}