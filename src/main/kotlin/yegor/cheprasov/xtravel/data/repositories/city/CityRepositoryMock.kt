package yegor.cheprasov.xtravel.data.repositories.city

import yegor.cheprasov.xtravel.data.database.entities.cities.CityDTO

class CityRepositoryMock : CityRepository {
    override suspend fun insert(cityDTO: CityDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<CityDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): CityDTO? {
        TODO("Not yet implemented")
    }
}