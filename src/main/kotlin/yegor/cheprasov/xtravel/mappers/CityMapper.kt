package yegor.cheprasov.xtravel.mappers

import yegor.cheprasov.xtravel.data.database.dto.cities.CityDTO
import yegor.cheprasov.xtravel.entities.CityRemoteResponseShortEntity

object CityMapper {

    fun mapCityDTOtoShortNetwork(cityDTO: CityDTO, countyMainFolderName: String): CityRemoteResponseShortEntity =
        CityRemoteResponseShortEntity(
            cityId = cityDTO.id,
            nameEn = cityDTO.nameEn,
            nameRu = cityDTO.nameRu,
            previewURL = getPreviewURL(cityDTO, countyMainFolderName),
        )

    private fun getPreviewURL(cityDTO: CityDTO, countyMainFolderName: String): String =
        "resources/countries/$countyMainFolderName/cities/$cityDTO/images/main.jpg"


}