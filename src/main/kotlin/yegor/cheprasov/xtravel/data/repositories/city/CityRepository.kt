package yegor.cheprasov.xtravel.data.repositories.city

import yegor.cheprasov.xtravel.data.database.dto.cities.CityDTO

interface CityRepository {

    suspend fun getAll(): List<CityDTO>

    suspend fun getById(id: Long): CityDTO?

}