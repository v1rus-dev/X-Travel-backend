package yegor.cheprasov.xtravel.features.city

import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.utils.FileService

class CityController : KoinComponent {

    private val cityRepository: CityRepository by inject()
    private val fileService: FileService by inject()

    suspend fun fetchAllCities(call: RoutingCall) {

    }

    suspend fun fetchCityInfo(call: RoutingCall) {

    }

}