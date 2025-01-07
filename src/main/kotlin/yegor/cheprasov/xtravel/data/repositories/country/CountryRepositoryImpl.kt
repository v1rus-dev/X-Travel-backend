package yegor.cheprasov.xtravel.data.repositories.country

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO
import yegor.cheprasov.xtravel.data.database.tables.CountryInfoTable
import yegor.cheprasov.xtravel.data.database.tables.CountryLocalizationTable
import yegor.cheprasov.xtravel.data.database.tables.CountryTable

class CountryRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CountryRepository {

    override suspend fun fetchByCountryId(countryId: Long): Deferred<CountryDTO?> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            val country =
                databaseProvider.dbQuery { CountryTable.select { CountryTable.id.eq(countryId) }.single() }
            country.mapToCountryDTO()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun fetchAllCountries(): Deferred<List<CountryDTO>> = suspendedTransactionAsync {
        return@suspendedTransactionAsync  try {
            (CountryTable innerJoin CountryInfoTable innerJoin CountryLocalizationTable)
                .select { CountryLocalizationTable.languageCode eq "ru"  }
                .map { it.mapToCountryDTO() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun fetchAllCountriesShort(): Deferred<List<ShortCountryDTO>> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            (CountryTable innerJoin CountryLocalizationTable)
                .select { CountryLocalizationTable.languageCode eq "ru"  }
                .map { it.mapToShortCountryDTO() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun ResultRow.mapToCountryDTO(): CountryDTO =
        CountryDTO(
            countryId = this[CountryTable.id].value,
            countryName = this[CountryLocalizationTable.name],
            countryDescription = this[CountryLocalizationTable.description],
            area = this[CountryInfoTable.areaKm2],
            folderName = this[CountryTable.folderName],
            shortName = this[CountryLocalizationTable.shortName],
        )

    private fun ResultRow.mapToShortCountryDTO(): ShortCountryDTO =
        ShortCountryDTO(
            countryId = this[CountryTable.id].value,
            countryName = this[CountryLocalizationTable.name],
            folderName = this[CountryTable.folderName],
            shortName = this[CountryLocalizationTable.shortName],
        )
}