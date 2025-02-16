package yegor.cheprasov.xtravel.features.country

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.attraction.AttractionRepository
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.features.city.mapper.CityMapper
import yegor.cheprasov.xtravel.features.country.mapper.CountryMapper
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getLang
import yegor.cheprasov.xtravel.utils.getWebAddress

class CountryController : KoinComponent {

    private val countryRepository: CountryRepository by inject()
    private val cityRepository: CityRepository by inject()
    private val attractionRepository: AttractionRepository by inject()
    private val fileService: FileService by inject()

    suspend fun getCountries(call: ApplicationCall) {
        val lang = call.getLang()
        val countries = countryRepository.fetchAllCountriesShort(lang).await()

        val mappedCountries = countries.map {
            CountryMapper.mapToShort(it, fileService, call.getWebAddress())
        }.filter { it.imageUrl.isNotEmpty() }

        call.respond(HttpStatusCode.OK, CountryResponseRemote(countries = mappedCountries))
    }

    suspend fun getCountryInfo(call: ApplicationCall) {
        val lang = call.getLang()
        val countryId = call.parameters["country_id"]?.toLong() ?: return call.respond(
            HttpStatusCode.BadRequest,
            message = "Country id cannot be cast to long"
        )
        val countryInfo =
            countryRepository.fetchCountryInfo(countryId, lang).await() ?: return call.respond(HttpStatusCode.NotFound)

        val mappedInfo: CountryInfoResponseRemote =
            CountryMapper.mapToCountryInfo(countryInfo, fileService, call.getWebAddress())

        println("Country info: $mappedInfo")

        call.respond(HttpStatusCode.OK, mappedInfo)
    }

    suspend fun getCitiesForCountry(call: ApplicationCall) {
        val lang = call.getLang()
        val countryId = call.parameters["country_id"]?.toLong() ?: return call.respond(HttpStatusCode.BadRequest)
        val cities = cityRepository.fetchShortCitiesByCountryId(countryId, lang).await()
        val capital = cityRepository.fetchCapitalByCountryId(countryId, lang).await()

        val mappedCities = cities.map { CityMapper.mapToShort(it, fileService, call.getWebAddress()) }
        val mappedCapital = capital?.let { CityMapper.mapToShort(it, fileService, call.getWebAddress()) }

        call.respond(HttpStatusCode.OK, CountryCitiesResponseRemote(capital = mappedCapital, list = mappedCities))
    }

    suspend fun getAttractionsForCountry(call: ApplicationCall) {
        val lang = call.getLang()
        val countryId = call.parameters["country_id"]?.toLong() ?: return call.respond(HttpStatusCode.BadRequest)
        val attractions = attractionRepository.fetchAttractionsByCountryId(countryId, lang).await()
        println("Attractions size list: ${attractions.size}")

        val mappedAttractions = attractions.map { attraction ->
            CountryMapper.mapToShortAttraction(
                attraction,
                fileService,
                call.getWebAddress()
            )
        }

        println("Attractions: $attractions")
        call.respond(HttpStatusCode.OK, CountryAttractionsResponseRemote(list = mappedAttractions))
    }

}