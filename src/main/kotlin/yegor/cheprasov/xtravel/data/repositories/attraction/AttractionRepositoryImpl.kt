package yegor.cheprasov.xtravel.data.repositories.attraction

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider

class AttractionRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : AttractionRepository {
    override suspend fun fetchAttractionsByCountryId(countryId: Long, lang: String): Deferred<List<String>> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync emptyList<String>()
        }
}