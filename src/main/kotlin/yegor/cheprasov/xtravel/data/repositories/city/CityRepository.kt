package yegor.cheprasov.xtravel.data.repositories.city

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.cities.CityDTO
import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO

interface CityRepository {

    suspend fun getAll(): List<CityDTO>

    suspend fun getById(id: Long): CityDTO?

    suspend fun fetchCitiesShort(lang: String): Deferred<List<ShortCityDTO>>

    suspend fun fetchShortCitiesByCountryId(countryId: Long, lang: String): Deferred<List<ShortCityDTO>>

}