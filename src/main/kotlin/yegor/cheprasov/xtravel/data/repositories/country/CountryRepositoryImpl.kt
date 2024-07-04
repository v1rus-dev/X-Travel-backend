package yegor.cheprasov.xtravel.data.repositories.country

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.entities.country.CountryDTO
import yegor.cheprasov.xtravel.data.database.entities.country.ShortCountryDTO
import yegor.cheprasov.xtravel.data.repositories.city.City
import yegor.cheprasov.xtravel.data.repositories.country.Countries.countryNameEn
import yegor.cheprasov.xtravel.data.repositories.country.Countries.countryNameRu
import yegor.cheprasov.xtravel.data.repositories.country.Countries.flagUrl

class CountryRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CountryRepository {

    override suspend fun insert(countryDTO: CountryDTO) {
        databaseProvider.dbQuery {
            Countries.insert {
                it[countryNameEn] = countryDTO.countryNameEn
                it[countryNameRu] = countryDTO.countryNameRu
                it[countryDescriptionEn] = countryDTO.countryDescriptionEn
                it[countryDescriptionRu] = countryDTO.countryDescriptionRu
                it[flagUrl] = countryDTO.flagUrl
                it[capitalId] = countryDTO.capitalId
                it[population] = countryDTO.population
            }
        }
    }

    override suspend fun fetchByCountryId(countryId: Long): CountryDTO? {
        return try {
            val country =
                databaseProvider.dbQuery { Countries.select { Countries.countryDescriptionRu.eq("") }.single() }
            country.mapToCountryDTO()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun fetchAllCountries(): List<CountryDTO> {
        return try {
            val list = databaseProvider.dbQuery { Countries.selectAll() }.toCountryDTOList()
            list
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun fetchTrendingCountry(): List<ShortCountryDTO> {
        return try {
            databaseProvider.dbQuery {
                Countries.innerJoin(City, onColumn = { id }, otherColumn = { countryId }).slice(
                    Countries.id,
                    countryNameEn,
                    countryNameRu,
                    flagUrl,
                    City.id,
                    City.nameEn,
                    City.nameRu,
                    Countries.mainFolderName,
                )
                    .selectAll()
                    .limit(5)
                    .map {
                        ShortCountryDTO(
                            countryId = it[Countries.id].value,
                            countryNameEn = it[countryNameEn],
                            countryNameRu = it[countryNameRu],
                            flagUrl = it[flagUrl],
                            capitalId = it[City.id].value,
                            capitalNameEn = it[City.nameEn],
                            capitalNameRu = it[City.nameRu],
                            mainFolderName = it[Countries.mainFolderName]
                        )
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun Query.toCountryDTOList(): List<CountryDTO> =
        this.map { country ->
            country.mapToCountryDTO()
        }

    private fun ResultRow.mapToCountryDTO(): CountryDTO =
        CountryDTO(
            countryId = this[Countries.id].value,
            countryNameEn = this[countryNameEn],
            countryNameRu = this[countryNameRu],
            countryDescriptionEn = this[Countries.countryDescriptionEn],
            countryDescriptionRu = this[Countries.countryDescriptionRu],
            flagUrl = this[flagUrl],
            capitalId = this[Countries.capitalId],
            population = this[Countries.population],
            mainFolderName = this[Countries.mainFolderName]
        )
}

object Countries : IdTable<Int>() {
    override val id: Column<EntityID<Int>> = integer("id").entityId()

    val countryNameEn = varchar("country_name_en", 60)
    val countryNameRu = varchar("country_name_ru", 60)
    val countryDescriptionEn = text("country_description_en")
    val countryDescriptionRu = text("country_description_ru")
    val flagUrl = text("flag_url").nullable()
    val capitalId = varchar("capital_id", 50)
    val population = integer("population")
    val mainFolderName = varchar("main_folder_name", 30)
}