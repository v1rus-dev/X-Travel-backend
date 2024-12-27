package yegor.cheprasov.xtravel.data.repositories.city

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.cities.CityDTO
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
           val city = databaseProvider.dbQuery { CountryTable.select { CountryTable.id.eq(id) }.single() }
           city.mapToCityDTO()
       } catch (e: Exception) {
           e.printStackTrace()
           null
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
            id = this[CityTable.id].value,
            nameEn = this[CityTable.nameEn],
            nameRu = this[CityTable.nameRu],
            descriptionEn = this[CityTable.descriptionEn],
            descriptionRu = this[CityTable.descriptionRu],
            population = this[CityTable.population],
            latitude = this[CityTable.latitude],
            longitude = this[CityTable.longitude],
            countryId = this[CityTable.countryId].value,
            folderName = this[CityTable.folderName],
        )

}