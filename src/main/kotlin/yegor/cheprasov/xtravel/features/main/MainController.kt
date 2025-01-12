package yegor.cheprasov.xtravel.features.main

import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.features.country.mapper.CountryMapper
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getWebAddress

class MainController : KoinComponent {

    private val countryRepository: CountryRepository by inject()
    private val cityRepository: CityRepository by inject()
    private val fileService: FileService by inject()

    suspend fun fetchCountries(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
        val countries = countryRepository.fetchAllCountriesShort(lang).await()

        val mappedCountries = countries.map {
            CountryMapper.mapToShort(it, fileService, call.getWebAddress())
        }
    }

    suspend fun fetchCities(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
    }

    suspend fun fetchAttractions(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
    }

}