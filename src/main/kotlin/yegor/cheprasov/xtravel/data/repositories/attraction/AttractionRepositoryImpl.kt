package yegor.cheprasov.xtravel.data.repositories.attraction

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.attractions.ShortAttractionDTO
import yegor.cheprasov.xtravel.data.database.tables.*
import yegor.cheprasov.xtravel.plugins.City

class AttractionRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : AttractionRepository {
    override suspend fun fetchShortAttractionsByCountryId(
        countryId: Long,
        lang: String
    ): Deferred<List<ShortAttractionDTO>> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                (AttractionTable
                        innerJoin AttractionInfoTable
                        innerJoin AttractionLocalizationTable
                        leftJoin CountryTable
                        leftJoin CityTable
                        innerJoin CityLocalizationTable
                        )
                    .selectAll()
                    .where {
                        (AttractionTable.countryId eq countryId) and
                                (AttractionLocalizationTable.languageCode eq lang) and
                                (CityLocalizationTable.languageCode eq lang) and
                                (CountryTable.id eq AttractionTable.countryId) and  // Вместо ON
                                (CityTable.id eq AttractionTable.cityId) and        // Вместо ON
                                (CityLocalizationTable.cityId eq CityTable.id)      // Вместо ON
                    }
                    .map {
                        it.mapToShortAttractionDTO()
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList<ShortAttractionDTO>()
            }
        }

    override suspend fun fetchShortAttractionByCityId(cityId: Long, lang: String): Deferred<List<ShortAttractionDTO>> =
        suspendedTransactionAsync {
            println("Fetch attraction by city Id: $cityId")
            return@suspendedTransactionAsync try {
                (AttractionTable
                        innerJoin AttractionLocalizationTable
                        leftJoin CountryTable
                        leftJoin CityTable
                        innerJoin CityLocalizationTable
                        )
                    .selectAll()
                    .where {
                        (AttractionTable.cityId eq cityId) and
                                (AttractionLocalizationTable.languageCode eq lang) and
                                (CityLocalizationTable.languageCode eq lang) and
                                (CountryTable.id eq AttractionTable.countryId) and  // Вместо ON
                                (CityTable.id eq AttractionTable.cityId) and        // Вместо ON
                                (CityLocalizationTable.cityId eq CityTable.id)      // Вместо ON
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