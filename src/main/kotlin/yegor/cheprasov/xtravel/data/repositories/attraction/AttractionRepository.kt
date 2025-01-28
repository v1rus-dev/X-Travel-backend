package yegor.cheprasov.xtravel.data.repositories.attraction

import kotlinx.coroutines.Deferred

interface AttractionRepository {

    suspend fun fetchAttractionsByCountryId(countryId: Long, lang: String): Deferred<List<String>>

}