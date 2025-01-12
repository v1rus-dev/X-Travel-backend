package yegor.cheprasov.xtravel.data.repositories.country

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO

interface CountryRepository {

    suspend fun fetchByCountryId(countryId: Long, lang: String): Deferred<CountryDTO?>

    suspend fun fetchAllCountries(lang: String): Deferred<List<CountryDTO>>

    suspend fun fetchAllCountriesShort(lang: String): Deferred<List<ShortCountryDTO>>

}