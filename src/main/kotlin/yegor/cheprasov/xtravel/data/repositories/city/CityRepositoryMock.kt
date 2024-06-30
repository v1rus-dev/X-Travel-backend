package yegor.cheprasov.xtravel.data.repositories.city

import yegor.cheprasov.xtravel.data.database.entities.cities.CityDTO

class CityRepositoryMock : CityRepository {
    override suspend fun insert(cityDTO: CityDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<CityDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): CityDTO? {
        TODO("Not yet implemented")
    }
}

object CityMockFactory : CityRepository {
    override suspend fun insert(cityDTO: CityDTO) = Unit

    override suspend fun getAll(): List<CityDTO> =
        listOf()

    override suspend fun getById(id: String): CityDTO? {
        TODO("Not yet implemented")
    }

    enum class CityMock(
        val cityId: String,
        val nameEn: String,
        val nameRu: String,
        val descriptionEn: String,
        val descriptionRu: String,
        val imagesUrls: List<String>,
        val population: Int,
        val latitude: Double,
        val longitude: Double,
        val mainFolderName: String
    ) {
        /**
         * Japan
         */
        Tokyo(
            cityId = "tokyo_id",
            nameEn = "Tokyo",
            nameRu = "Токио",
            descriptionEn = "Tokyo description",
            descriptionRu = "Токио описание",
            imagesUrls = listOf(),
            population = 13_960_000,
            latitude = 35.652832,
            longitude = 139.839478,
            mainFolderName = "tokyo"
        ),
        Osaka(
            cityId = "city_id",
            nameEn = "Osaka",
            nameRu = "Осака",
            descriptionEn = "Osaka description",
            descriptionRu = "Осака описание",
            imagesUrls = listOf(),
            population = 2_691_000,
            latitude = 34.672314,
            longitude = 135.484802,
            mainFolderName = "osaka"
        ),

        /**
         * USA
         */
        Washington(
            cityId = "",
            nameEn = "",
            nameRu = "",
            descriptionEn = "",
            descriptionRu = "",
            imagesUrls = listOf(),
            population = 0,
            latitude = 0.0,
            longitude = 0.0,
            mainFolderName = ""
        )
    }

}

