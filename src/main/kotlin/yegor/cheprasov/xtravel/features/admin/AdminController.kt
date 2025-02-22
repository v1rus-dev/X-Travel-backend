package yegor.cheprasov.xtravel.features.admin

import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.attraction.AttractionRepository
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository

class AdminController : KoinComponent {

    private val cityRepository: CityRepository by inject()
    private val countryRepository: CountryRepository by inject()
    private val attractionRepository: AttractionRepository by inject()

    suspend fun getAllCities(call: ApplicationCall) {
        val list = cityRepository.getAll();
        println("Cities: ${list}")
    }

    suspend fun getAllCountries(call: ApplicationCall) {
//        val list = countryRepository.fetchAllCountries()
    }

    suspend fun getAllAttractions(call: ApplicationCall) {

    }

    suspend fun updateCountry(call: ApplicationCall) {

    }

    suspend fun deleteCountry(call: ApplicationCall) {

    }

    suspend fun updateCity(call: ApplicationCall) {

    }

    suspend fun deleteCity(call: ApplicationCall) {

    }

    suspend fun updateAttraction(call: ApplicationCall) {

    }

    suspend fun deleteAttraction(call: ApplicationCall) {}

}