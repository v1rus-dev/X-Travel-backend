package yegor.cheprasov.xtravel.features.country

import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.mappers.CountryMapper

class CountryController(
    private val call: ApplicationCall
) : KoinComponent {

    private val countryRepository: CountryRepository by inject()

    suspend fun getCountries() {
        val countries = countryRepository.fetchTrendingCountry()

        val response = CountryResponseRemote(
            countries = countries.map { CountryMapper.mapShortDTOtoNetwork(it) }
        )

        call.respond(response)
    }

}