package yegor.cheprasov.xtravel.data.repositories.attraction

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.attractions.ShortAttractionDTO
import yegor.cheprasov.xtravel.data.database.tables.*

class AttractionRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : AttractionRepository {
    override suspend fun fetchAttractionsByCountryId(
        countryId: Long,
        lang: String
    ): Deferred<List<ShortAttractionDTO>> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                (AttractionTable innerJoin AttractionInfoTable innerJoin AttractionLocalizationTable innerJoin CountryTable leftJoin CityTable)
                    .selectAll()
                    .where {
                        AttractionTable.countryId eq countryId and AttractionLocalizationTable.languageCode.eq(lang) and CountryTable.id.eq(
                            countryId
                        )
                    }
                    .map {
                        it.mapToShortAttractionDTO()
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList<ShortAttractionDTO>()
            }
        }

    private fun ResultRow.mapToShortAttractionDTO(): ShortAttractionDTO = ShortAttractionDTO(
        attractionId = this[AttractionTable.id].value,
        name = this[AttractionLocalizationTable.name],
        countryId = this[AttractionTable.countryId].value,
        cityId = this[AttractionTable.cityId]?.value,
        countryFolderName = this[CountryTable.folderName],
        folderName = this[AttractionTable.folderName],
        cityName = this[CityLocalizationTable.name]
    )
}