package yegor.cheprasov.xtravel.features.city.mapper

import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.features.main.ShortCity
import yegor.cheprasov.xtravel.utils.FileService

object CityMapper {
    fun mapToShort(dto: ShortCityDTO, fileService: FileService, currentAddress: String): ShortCity {
        val listAllFiles =
            fileService.listAllFiles("countries/${dto.parentCountryFolderName}/cities/${dto.cityFolderName}")
        val mainImage = listAllFiles.firstOrNull { it.contains("main.jpg") } ?: listAllFiles.firstOrNull()
        return ShortCity(
            cityId = dto.cityId,
            countryId = dto.countryId,
            countryName = dto.countryName,
            cityName = dto.cityName,
            imageUrl = if (mainImage != null) {
                "$currentAddress/resources/$mainImage"
            } else {
                ""
            }
        )
    }
}