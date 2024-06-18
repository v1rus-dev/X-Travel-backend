package yegor.cheprasov.xtravel.features.country

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponseRemote(
    val countries: List<Country>
)

@Serializable
data class Country(
    val name: String
)