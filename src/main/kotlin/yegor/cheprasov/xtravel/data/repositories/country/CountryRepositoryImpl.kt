package yegor.cheprasov.xtravel.data.repositories.country

import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.country.CountryDTO

class CountryRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CountryRepository {

    private val list: List<CountryDTO> = listOf(
        CountryDTO("USA"),
        CountryDTO("Japan"),
        CountryDTO("Belarus"),
        CountryDTO("Latvia"),
        CountryDTO("Netherlands"),
        CountryDTO("Germany"),
        CountryDTO("Italy"),
        CountryDTO("Spain"),
        CountryDTO("Canada"),
        CountryDTO("Mexico")
    )

    override fun fetchCountries(): List<CountryDTO> = list
}