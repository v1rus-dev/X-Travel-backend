package yegor.cheprasov.xtravel.features.country

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.features.country.mapper.CountryMapper
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getWebAddress

class CountryController : KoinComponent {

    private val countryRepository: CountryRepository by inject()
    private val fileService: FileService by inject()

    suspend fun getCountries(call: ApplicationCall) {
        val countries = countryRepository.fetchAllCountriesShort().await()

        val mappedCountries = countries.map {
            CountryMapper.mapToShort(it, fileService, call.getWebAddress())
        }.filter { it.imageUrl.isNotEmpty() }

        call.respond(HttpStatusCode.OK, CountryResponseRemote(countries = mappedCountries))
    }

    suspend fun getCountryInfo(call: ApplicationCall) {

    }

    suspend fun getCitiesForCountry(call: ApplicationCall) {

    }

    suspend fun getAttractionsForCountry(call: ApplicationCall) {

    }

}