package yegor.cheprasov.xtravel.features.city.mapper

import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.features.city.ShortCity
import yegor.cheprasov.xtravel.utils.FileService

object CityMapper {
    fun mapToShort(dto: ShortCityDTO, fileService: FileService, currentAddress: String): ShortCity {
//        val listAllFiles = fileService.listAllFiles()
        return ShortCity(cityId = 0, cityName = "City name", folderName = "")
    }
}