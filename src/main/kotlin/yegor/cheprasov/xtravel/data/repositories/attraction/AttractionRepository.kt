package yegor.cheprasov.xtravel.data.repositories.attraction

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.attractions.ShortAttractionDTO

interface AttractionRepository {

    suspend fun fetchAttractionsByCountryId(countryId: Long, lang: String): Deferred<List<ShortAttractionDTO>>

}