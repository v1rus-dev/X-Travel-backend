package yegor.cheprasov.xtravel.features.city.mapper

import yegor.cheprasov.xtravel.data.database.dto.cities.CityInfoDTO
import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCapitalCityDTO
import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.features.city.CityInfo
import yegor.cheprasov.xtravel.features.main.ShortCity
import yegor.cheprasov.xtravel.features.main.ShortCityCapital
import yegor.cheprasov.xtravel.utils.FileService

object CityMapper {

    fun mapToCityInfo(dto: CityInfoDTO, fileService: FileService, currentAddress: String): CityInfo {
        val listAllFiles = fileService.listAllFilesForCity(dto.countryFolderName, dto.cityFolderName)
        val mainImage = listAllFiles.firstOrNull { it.contains("main.jpg") } ?: listAllFiles.firstOrNull()
        return CityInfo(
            cityId = dto.id,
            name = dto.name,
            description = dto.description,
            latitude = dto.latitude,
            longitude = dto.longitude,
            countryId = dto.countryId,
            countryName = dto.countryName,
            mainImage = mainImage?.let {
                "$currentAddress/resources/$it"
            } ?: "",
            photos = listAllFiles.sortedByDescending { it.contains("main") }.map { "$currentAddress/resources/$it" }
        )
    }

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

    fun mapToShort(dto: ShortCapitalCityDTO, fileService: FileService, currentAddress: String): ShortCityCapital {
        val listAllFiles =
            fileService.listAllFiles("countries/${dto.parentCountryFolderName}/cities/${dto.cityFolderName}")
        val mainImage = listAllFiles.firstOrNull { it.contains("main.jpg") } ?: listAllFiles.firstOrNull()
        return ShortCityCapital(
            cityId = dto.cityId,
            countryId = dto.countryId,
            cityName = dto.cityName,
            imageUrl = if (mainImage != null) {
                "$currentAddress/resources/$mainImage"
            } else {
                ""
            }
        )
    }
}