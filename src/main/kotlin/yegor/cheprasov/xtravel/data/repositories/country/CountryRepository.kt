package yegor.cheprasov.xtravel.data.repositories.country

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO

interface CountryRepository {

    suspend fun fetchByCountryId(countryId: Long): CountryDTO?

    suspend fun fetchAllCountries(): List<CountryDTO>

    suspend fun fetchAllCountriesShort(): Deferred<List<ShortCountryDTO>>

}