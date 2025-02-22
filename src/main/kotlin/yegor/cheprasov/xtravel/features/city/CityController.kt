package yegor.cheprasov.xtravel.features.city

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.attraction.AttractionRepository
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.features.city.mapper.CityMapper
import yegor.cheprasov.xtravel.features.country.CountryAttractionsResponseRemote
import yegor.cheprasov.xtravel.features.mappers.AttractionMapper
import yegor.cheprasov.xtravel.utils.FileService
import yegor.cheprasov.xtravel.utils.getLang
import yegor.cheprasov.xtravel.utils.getWebAddress

class CityController : KoinComponent {

    private val cityRepository: CityRepository by inject()
    private val attractionRepository: AttractionRepository by inject()
    private val fileService: FileService by inject()

    suspend fun fetchCityInfo(call: RoutingCall) {
        val land = call.getLang()
        val cityId = call.parameters["city_id"]?.toLong() ?: return call.respond(HttpStatusCode.BadRequest, "City ID is cannot be cast to long")
        val cityInfo = cityRepository.fetchCityInfo(cityId, lang = land).await() ?: return call.respond(HttpStatusCode.NotFound, "City info is unknown")
        val mappedInfo = CityMapper.mapToCityInfo(cityInfo, fileService, call.getWebAddress())

        if (mappedInfo.mainImage.isEmpty()) {
            return call.respond(HttpStatusCode.NotFound, "Image is empty")
        }

        call.respond(HttpStatusCode.OK, mappedInfo)
    }

    suspend fun fetchAttractions(call: RoutingCall) {
        val lang = call.getLang()
        val cityId = call.parameters["city_id"]?.toLong() ?: return call.respond(HttpStatusCode.BadRequest, "City ID is cannot be cast to long")
        val attractions = attractionRepository.fetchShortAttractionByCityId(cityId = cityId, lang = lang).await()

        val mappedAttractions = attractions.map { attraction ->
            AttractionMapper.mapToShortAttraction(
                attraction,
                fileService,
                call.getWebAddress()
            )
        }

        println("Attractions: $attractions")
        call.respond(HttpStatusCode.OK, CountryAttractionsResponseRemote(list = mappedAttractions))
    }

}