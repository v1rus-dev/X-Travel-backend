package yegor.cheprasov.xtravel.data.repositories.city

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.cities.CityDTO
import yegor.cheprasov.xtravel.data.database.dto.cities.ShortCityDTO
import yegor.cheprasov.xtravel.data.database.tables.CityLocalizationTable
import yegor.cheprasov.xtravel.data.database.tables.CityTable
import yegor.cheprasov.xtravel.data.database.tables.CountryLocalizationTable
import yegor.cheprasov.xtravel.data.database.tables.CountryTable

class CityRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CityRepository {

    override suspend fun getAll(): List<CityDTO> {
        return try {
            val cities = databaseProvider.dbQuery { CityTable.selectAll() }
            cities.toCityDTOList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getById(id: Long): CityDTO? {
        return try {
            val city = databaseProvider.dbQuery { CountryTable.selectAll().where(CountryTable.id.eq(id)) }.single()
            city.mapToCityDTO()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun fetchCitiesShort(lang: String): Deferred<List<ShortCityDTO>> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            (CityTable innerJoin CityLocalizationTable innerJoin CountryTable innerJoin CountryLocalizationTable)
                .selectAll()
                .where(CityLocalizationTable.languageCode.eq(lang).and(CountryLocalizationTable.languageCode.eq(lang)))
                .map { it.mapToShortCityDTO() }

        } catch (e: Exception) {
            e.printStackTrace();
            emptyList()
        }
    }

    override suspend fun fetchShortCitiesByCountryId(countryId: Long, lang: String): Deferred<List<ShortCityDTO>> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                (CityTable innerJoin CityLocalizationTable innerJoin CountryTable innerJoin CountryLocalizationTable)
                    .selectAll()
                    .where(
                        CityTable.countryId.eq(countryId).and(CityLocalizationTable.languageCode.eq(lang))
                            .and(CountryLocalizationTable.languageCode.eq(lang))
                    )
                    .map { it.mapToShortCityDTO() }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }

    private suspend fun Query.toCityDTOList(): List<CityDTO> =
        databaseProvider.dbQuery {
            this.map { city ->
                city.mapToCityDTO()
            }
        }

    private fun ResultRow.mapToCityDTO(): CityDTO =
        CityDTO(
            cityId = this[CityTable.id].value,
            countryId = this[CountryTable.id].value,
            internalName = this[CityTable.internalName],
            folderName = this[CityTable.folderName],
        )

    private fun ResultRow.mapToShortCityDTO(): ShortCityDTO =
        ShortCityDTO(
            cityId = this[CityTable.id].value,
            countryId = this[CountryTable.id].value,
            cityName = this[CityLocalizationTable.name],
            countryName = this[CountryLocalizationTable.name],
            cityFolderName = this[CityTable.folderName],
            parentCountryId = this[CountryTable.id].value,
            parentCountryFolderName = this[CountryTable.folderName],
        )

}