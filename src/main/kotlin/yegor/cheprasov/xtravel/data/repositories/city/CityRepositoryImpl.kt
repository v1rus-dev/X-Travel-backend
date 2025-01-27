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
            (CityTable innerJoin CityLocalizationTable)
                .selectAll()
                .where(CityLocalizationTable.languageCode.eq(lang))
                .map { it.mapToShortCityDTO() }

        } catch (e: Exception) {
            e.printStackTrace();
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
            cityName = this[CityLocalizationTable.name],
            folderName = this[CityTable.folderName]
        )

}