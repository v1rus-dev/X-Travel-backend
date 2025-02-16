package yegor.cheprasov.xtravel.data.repositories.country

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.dto.country.CountryInfoDTO
import yegor.cheprasov.xtravel.data.database.dto.country.ShortCountryDTO
import yegor.cheprasov.xtravel.data.database.tables.CountryInfoTable
import yegor.cheprasov.xtravel.data.database.tables.CountryLocalizationTable
import yegor.cheprasov.xtravel.data.database.tables.CountryTable
import yegor.cheprasov.xtravel.data.database.tables.CurrencyTable
import java.util.Currency

class CountryRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CountryRepository {

    override suspend fun fetchCountryInfo(countryId: Long, lang: String): Deferred<CountryInfoDTO?> =
        suspendedTransactionAsync {
            println("Request: $countryId")
            return@suspendedTransactionAsync try {
                (CountryTable innerJoin CountryInfoTable innerJoin CountryLocalizationTable innerJoin CurrencyTable)
                    .selectAll()
                    .where {
                        CountryTable.id eq countryId and CountryLocalizationTable.languageCode.eq(lang)
                    }
                    .singleOrNull()?.mapToCountryInfoDTO()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun fetchByCountryId(countryId: Long, lang: String): Deferred<CountryDTO?> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                val country =
                    databaseProvider.dbQuery { CountryTable.selectAll().where(CountryTable.id.eq(countryId)) }.single()
                country.mapToCountryDTO()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun fetchAllCountries(lang: String): Deferred<List<CountryDTO>> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            (CountryTable innerJoin CountryInfoTable innerJoin CountryLocalizationTable)
                .selectAll()
                .where(CountryLocalizationTable.languageCode.eq(lang))
                .map { it.mapToCountryDTO() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun fetchAllCountriesShort(lang: String): Deferred<List<ShortCountryDTO>> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                (CountryTable innerJoin CountryLocalizationTable)
                    .selectAll()
                    .where(CountryLocalizationTable.languageCode.eq(lang))
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

    private fun ResultRow.mapToCountryInfoDTO(): CountryInfoDTO {
        println("Map to country info dto: $this")
        return CountryInfoDTO(
            countryId = this[CountryTable.id].value,
            name = this[CountryLocalizationTable.name],
            description = this[CountryLocalizationTable.description],
            shortName = this[CountryLocalizationTable.shortName] ?: "",
            folderName = this[CountryTable.folderName],
            phoneCode = this[CountryInfoTable.phoneCode],
            isoCode = this[CountryInfoTable.isoCode],
            areaKm2 = this[CountryInfoTable.areaKm2],
            currencyCode = this[CurrencyTable.currencyCode],
            currencyFontCode = this[CurrencyTable.fontCode]
        )
    }

}