package yegor.cheprasov.xtravel.data.repositories.country

import yegor.cheprasov.xtravel.data.database.country.CountryDTO

interface CountryRepository {

    fun fetchCountries(): List<CountryDTO>

}