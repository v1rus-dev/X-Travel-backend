package yegor.cheprasov.xtravel.features.main

import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.entities.CountryRemoteResponseShortEntity

class MainController(
    private val call: ApplicationCall
) : KoinComponent {

    private val countryRepository: CountryRepository by inject()

    suspend fun getMain() {
        val counties: List<ShortCountryDTO> = countryRepository.fetchTrendingCountry()

        call.respond(
            MainResponseRemote(trendingCountries = counties.map {
                CountryRemoteResponseShortEntity(
                    countryId = it.countryId,
                    mainPhotoUrl = it.mainPhotoUrl,
                    countryNameEn = it.countryNameEn,
                    countryNameRu = it.countryNameRu,
                    flagUrl = it.flagUrl,
                    capitalId = it.capitalId,
                    capitalNameEn = it.capitalNameEn,
                    capitalNameRu = it.capitalNameRu
                )
            })
        )
    }

}