package yegor.cheprasov.xtravel.features.city

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.features.city.mapper.CityMapper
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getWebAddress

class CityController : KoinComponent {

    private val cityRepository: CityRepository by inject()
    private val fileService: FileService by inject()

    suspend fun fetchAllCities(call: RoutingCall) {

    }

    suspend fun fetchCityInfo(call: RoutingCall) {

    }

    suspend fun fetchCitiesForMain(call: ApplicationCall) {
        val lang = call.request.headers["lang"] ?: "ru"
        val cities: List<ShortCityDTO> = cityRepository.fetchCitiesShort(lang).await()

        val mappedCities = cities.map {
            CityMapper.mapToShort(it, fileService, call.getWebAddress())
        }

        call.respond(HttpStatusCode.OK, CityShortResponseRemote(list = mappedCities))
    }

}