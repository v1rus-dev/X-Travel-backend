package yegor.cheprasov.xtravel.data.repositories.attraction

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.attractions.ShortAttractionDTO

interface AttractionRepository {

    suspend fun fetchShortAttractionsByCountryId(countryId: Long, lang: String): Deferred<List<ShortAttractionDTO>>

    suspend fun fetchShortAttractionByCityId(cityId: Long, lang: String): Deferred<List<ShortAttractionDTO>>

}