package yegor.cheprasov.xtravel.data.repositories.city

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.cities.*
import yegor.cheprasov.xtravel.data.database.dto.utils.LocalizationText
import yegor.cheprasov.xtravel.data.database.tables.*

class CityRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CityRepository {

    override suspend fun getCityFullInfo(cityId: Long): Deferred<FullCityDTO?> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            val city = (CityTable)
                .selectAll()
                .where { CityTable.id.eq(cityId) }
            val cityNames = (CityLocalizationTable)
                .selectAll()
                .where { CityTable.id.eq(cityId) }
                .toListLocalizationNameCity()
            val cityDescriptions = (CityLocalizationTable)
                .selectAll()
                .where { CityTable.id.eq(cityId) }
                .toListLocalizationDescCity()

            null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

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

    override suspend fun fetchCapitalByCountryId(countryId: Long, lang: String): Deferred<ShortCapitalCityDTO?> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                (CityTable innerJoin CityLocalizationTable innerJoin CountryTable)
                    .select(
                        CityTable.id,
                        CityTable.isCapital,
                        CityTable.countryId,
                        CityTable.folderName,
                        CityLocalizationTable.name,
                        CountryTable.folderName
                    )
                    .where(
                        CityTable.countryId.eq(countryId).and(CityLocalizationTable.languageCode.eq(lang))
                            .and(CityTable.isCapital).and(CountryTable.id.eq(countryId))
                    )
                    .singleOrNull()?.mapToShortCapitalDTO(countryId)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun fetchCityInfo(cityId: Long, lang: String): Deferred<CityInfoDTO?> = suspendedTransactionAsync {
        return@suspendedTransactionAsync try {
            (CityTable innerJoin CityLocalizationTable innerJoin CityInfoTable leftJoin CountryTable innerJoin CountryLocalizationTable)
                .selectAll()
                .where {
                    CityTable.id.eq(cityId).and(CityLocalizationTable.languageCode.eq(lang))
                        .and(CountryTable.id.eq(CityTable.countryId))
                        .and(CountryLocalizationTable.languageCode.eq(lang))
                }
                .singleOrNull()?.mapToCityInfoDTO()
        } catch (e: Exception) {
            e.printStackTrace()
            null
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

    private suspend fun Query.toListLocalizationNameCity(): List<LocalizationText> =
        databaseProvider.dbQuery {
            this.map { row ->
                LocalizationText(
                    lang = row[CityLocalizationTable.languageCode],
                    text = row[CityLocalizationTable.name],
                )
            }
        }

    private suspend fun Query.toListLocalizationDescCity(): List<LocalizationText> =
        databaseProvider.dbQuery {
            this.map { row ->
                LocalizationText(
                    lang = row[CityLocalizationTable.languageCode],
                    text = row[CityLocalizationTable.description],
                )
            }
        }

    private fun ResultRow.mapToCityInfoDTO(): CityInfoDTO =
        CityInfoDTO(
            id = this[CityTable.id].value,
            name = this[CityLocalizationTable.name],
            description = this[CityLocalizationTable.description],
            latitude = this[CityInfoTable.latitude],
            longitude = this[CityInfoTable.longitude],
            countryName = this[CountryLocalizationTable.name],
            countryId = this[CountryTable.id].value,
            countryFolderName = this[CountryTable.folderName],
            cityFolderName = this[CityTable.folderName]
        )

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
            isCapital = this[CityTable.isCapital],
        )

    private fun ResultRow.mapToShortCapitalDTO(countryId: Long): ShortCapitalCityDTO = ShortCapitalCityDTO(
        cityId = this[CityTable.id].value,
        countryId = countryId,
        cityName = this[CityLocalizationTable.name],
        cityFolderName = this[CityTable.folderName],
        parentCountryFolderName = this[CountryTable.folderName],
    )

}