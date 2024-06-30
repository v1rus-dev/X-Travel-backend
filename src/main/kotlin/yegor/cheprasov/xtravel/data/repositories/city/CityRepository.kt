package yegor.cheprasov.xtravel.data.repositories.city

import yegor.cheprasov.xtravel.data.database.entities.cities.CityDTO

interface CityRepository {

    suspend fun insert(cityDTO: CityDTO)

    suspend fun getAll(): List<CityDTO>

    suspend fun getById(id: String): CityDTO?

}