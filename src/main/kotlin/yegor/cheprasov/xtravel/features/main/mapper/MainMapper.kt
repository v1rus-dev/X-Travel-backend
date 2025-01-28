package yegor.cheprasov.xtravel.features.main.mapper

import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.features.main.ShortCity
import yegor.cheprasov.xtravel.utils.FileService

object MainMapper {

    fun mapCityToShort(city: ShortCityDTO, fileService: FileService, currentAddress: String): ShortCity {
        val listAllFiles = fileService.listAllFiles("countries/${city.parentCountryFolderName}/cities/${city.cityFolderName}/images")
        val mainImage = listAllFiles.firstOrNull { it.contains("main.jpg") } ?: listAllFiles.firstOrNull()
        return ShortCity(
            cityId = city.cityId,
            countryId = city.countryId,
            countryName = city.countryName,
            cityName = city.cityName,
            imageUrl = if (mainImage != null) {
                "$currentAddress/resources/$mainImage"
            } else {
                ""
            }
        )
    }

}