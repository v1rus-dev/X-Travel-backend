package yegor.cheprasov.xtravel.features.city

import io.ktor.server.application.*
import io.ktor.server.response.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository

class CityController(
    private val call: ApplicationCall
) : KoinComponent {

    private val cityRepository: CityRepository by inject()

    suspend fun getAllCities() {
        val cities = cityRepository.getAll().map {
            CityResponse(
                cityId = it.id,
                nameEn = it.nameEn,
                nameRu = it.nameRu,
                descriptionEn = it.descriptionEn,
                descriptionRu = it.descriptionRu,
                imagesUrls = emptyList(),
                population = it.population,
                latitude = it.latitude,
                longitude = it.longitude
            )
        }

        call.respond(
            GetAllCitiesResponseRemote(cities = cities)
        )
    }

}