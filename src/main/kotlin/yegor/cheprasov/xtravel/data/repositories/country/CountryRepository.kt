package yegor.cheprasov.xtravel.data.repositories.country

import yegor.cheprasov.xtravel.data.database.entities.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO

interface CountryRepository {

    suspend fun insert(countryDTO: CountryDTO)

    suspend fun fetchByCountryId(countryId: Long): CountryDTO?

    suspend fun fetchAllCountries(): List<CountryDTO>

    suspend fun fetchTrendingCountry(): List<ShortCountryDTO>

}