package yegor.cheprasov.xtravel.data.repositories.city

import yegor.cheprasov.xtravel.data.database.dto.cities.CityDTO
import yegor.cheprasov.xtravel.data.repositories.country.CountriesMockFactory

class CityRepositoryMock : CityRepository {

    override suspend fun getAll(): List<CityDTO> = CityMockFactory.CityMock.entries.map {
        CityMockFactory.getCity(it)
    }

    override suspend fun getById(id: Long): CityDTO? = getAll().find { it.id == id }
}

object CityMockFactory {

    fun getCity(cityMock: CityMock): CityDTO =
        CityDTO(
            id = cityMock.cityId,
            nameEn = cityMock.nameEn,
            nameRu = cityMock.nameRu,
            descriptionEn = cityMock.descriptionEn,
            descriptionRu = cityMock.descriptionRu,
            countryId = cityMock.countryId,
            population = cityMock.population,
            latitude = cityMock.latitude,
            longitude = cityMock.longitude,
            folderName = ""
        )

    enum class CityMock(
        val cityId: Long,
        val nameEn: String,
        val nameRu: String,
        val descriptionEn: String,
        val descriptionRu: String,
        val countryId: Long,
        val population: Int,
        val latitude: Double,
        val longitude: Double,
        val mainFolderName: String
    ) {
        /**
         * Japan
         */
        Tokyo(
            cityId = 0,
            nameEn = "Tokyo",
            nameRu = "Токио",
            descriptionEn = "Tokyo description",
            descriptionRu = "Токио описание",
            countryId = CountriesMockFactory.CountryMock.Japan.countryId,
            population = 13_960_000,
            latitude = 35.652832,
            longitude = 139.839478,
            mainFolderName = "tokyo"
        ),
        Osaka(
            cityId = 1,
            nameEn = "Osaka",
            nameRu = "Осака",
            descriptionEn = "Osaka description",
            descriptionRu = "Осака описание",
            countryId = CountriesMockFactory.CountryMock.Japan.countryId,
            population = 2_691_000,
            latitude = 34.672314,
            longitude = 135.484802,
            mainFolderName = "osaka"
        ),

        /**
         * USA
         */
        Washington(
            cityId = 2,
            nameEn = "",
            nameRu = "",
            descriptionEn = "",
            descriptionRu = "",
            countryId = CountriesMockFactory.CountryMock.USA.countryId,
            population = 0,
            latitude = 0.0,
            longitude = 0.0,
            mainFolderName = ""
        )
    }

}

