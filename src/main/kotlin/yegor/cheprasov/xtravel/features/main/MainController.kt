package yegor.cheprasov.xtravel.features.main

import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.features.country.ShortCountry
import yegor.cheprasov.xtravel.features.country.mapper.CountryMapper
import yegor.cheprasov.xtravel.features.main.mapper.MainMapper
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getWebAddress

class MainController : KoinComponent {

    private val countryRepository: CountryRepository by inject()
    private val cityRepository: CityRepository by inject()
    private val fileService: FileService by inject()

    suspend fun fetchCountries(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
        val countries = countryRepository.fetchAllCountriesShort(lang).await()

        val mappedCountries: List<ShortCountry> = countries.map {
            CountryMapper.mapToShort(it, fileService, call.getWebAddress())
        }

        val filteredCountries: List<ShortCountry> = mappedCountries.filter { it.imageUrl.isNotEmpty() }

        call.respond(MainCountriesResponseRemote(list = filteredCountries))
    }

    suspend fun fetchCities(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
        val cities: List<ShortCityDTO> = cityRepository.fetchCitiesShort(lang).await()

        val mappedCities: List<ShortCity> = cities.map {
            MainMapper.mapCityToShort(it, fileService, call.getWebAddress())
        }

        val filteredCities: List<ShortCity> = mappedCities.filter { it.imageUrl.isNotEmpty() }.shuffled().take(6)

        call.respond(MainCityResponseRemote(list = filteredCities))
    }

    suspend fun fetchAttractions(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
    }

}