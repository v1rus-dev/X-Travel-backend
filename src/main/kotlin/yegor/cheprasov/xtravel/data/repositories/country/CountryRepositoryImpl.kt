package yegor.cheprasov.xtravel.data.repositories.country

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO
import yegor.cheprasov.xtravel.data.database.tables.CountryTable

class CountryRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CountryRepository {

    override suspend fun fetchByCountryId(countryId: Long): CountryDTO? {
        return try {
            val country =
                databaseProvider.dbQuery { CountryTable.select { CountryTable.id.eq(countryId) }.single() }
            country.mapToCountryDTO()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun fetchAllCountries(): List<CountryDTO> {
        return try {
            val list = databaseProvider.dbQuery { CountryTable.selectAll() }.map { it.mapToCountryDTO() }
            list
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun fetchAllCountriesShort(): Deferred<List<ShortCountryDTO>> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            val list = databaseProvider.dbQuery { CountryTable.selectAll() }.map { it.mapToShortCountryDTO() }
            list
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun ResultRow.mapToCountryDTO(): CountryDTO =
        CountryDTO(
            countryId = this[CountryTable.id].value,
            countryNameRu = this[CountryTable.nameRu],
            countryNameEn = this[CountryTable.nameEn],
            countryDescriptionRu = this[CountryTable.descriptionRu],
            countryDescriptionEn = this[CountryTable.descriptionEn],
            population = this[CountryTable.population],
            latitude = this[CountryTable.latitude],
            longitude = this[CountryTable.longitude],
            folderName = this[CountryTable.folderName],
            shortName = this[CountryTable.shortName],
        )

    private fun ResultRow.mapToShortCountryDTO(): ShortCountryDTO =
        ShortCountryDTO(
            countryId = this[CountryTable.id].value,
            countryNameRu = this[CountryTable.nameRu],
            countryNameEn = this[CountryTable.nameEn],
            folderName = this[CountryTable.folderName],
            shortName = this[CountryTable.shortName],
        )
}