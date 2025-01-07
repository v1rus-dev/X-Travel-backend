package yegor.cheprasov.xtravel.features.main

import kotlinx.serialization.Serializable
import yegor.cheprasov.xtravel.entities.CityRemoteResponseShortEntity
import yegor.cheprasov.xtravel.features.country.ShortCountry

@Serializable
data class MainResponseRemote(
    val trendingCountries: List<ShortCountry>,
    val trendingCities: List<CityRemoteResponseShortEntity>
)

