package yegor.cheprasov.xtravel.features.city

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.attraction.AttractionRepository
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getLang

class CityController : KoinComponent {

    private val cityRepository: CityRepository by inject()
    private val attractionRepository: AttractionRepository by inject()
    private val fileService: FileService by inject()

    suspend fun fetchAllCities(call: RoutingCall) {

    }

    suspend fun fetchCityInfo(call: RoutingCall) {
        val land = call.getLang()
        val cityId = call.parameters["city_id"]?.toLong() ?: return call.respond(HttpStatusCode.BadRequest, "City ID is cannot be cast to long")
        val cityInfo = cityRepository.fetchCityInfo(cityId, lang = land).await() ?: return call.respond(HttpStatusCode.NotFound, "City info is unknown")
    }

    suspend fun fetchAttractions(call: RoutingCall) {

    }

}