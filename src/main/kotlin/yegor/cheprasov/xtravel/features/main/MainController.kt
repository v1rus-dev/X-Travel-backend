package yegor.cheprasov.xtravel.features.main

import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository

class MainController(
    private val call: ApplicationCall
) : KoinComponent {

    private val countryRepository: CountryRepository by inject()
    private val cityRepository: CityRepository by inject()

    suspend fun getMain() {
//        val counties: List<ShortCountryDTO> = countryRepository.fetchTrendingCountry()
//        val cities: List<CityDTO> = cityRepository.getAll()
//        call.respond(
//            MainResponseRemote(
//                trendingCountries = counties.map { country ->
//                    CountryMapper.mapShortDTOtoNetworkMain(country)
//                },
//                trendingCities = cities.map { city ->
//                    val country = countryRepository.fetchByCountryId(city.countryId)
//                    CityMapper.mapCityDTOtoShortNetwork(city, country?.mainFolderName ?: "")
//                }
//            )
//        )
    }

}