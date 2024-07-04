package yegor.cheprasov.xtravel.data.repositories.city

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.entities.cities.CityDTO
import yegor.cheprasov.xtravel.data.utils.stringList

class CityRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CityRepository {
    override suspend fun insert(cityDTO: CityDTO) {
        databaseProvider.dbQuery {
            City.insert {
                it[nameEn] = cityDTO.nameEn
                it[nameRu] = cityDTO.nameRu
                it[descriptionEn] = cityDTO.descriptionEn
                it[descriptionRu] = cityDTO.descriptionRu
                it[population] = cityDTO.population
                it[latitude] = cityDTO.latitude
                it[longitude] = cityDTO.longitude
                it[imagesUrls] = cityDTO.imagesUrls
            }
        }
    }

    override suspend fun getAll(): List<CityDTO> {
        return try {
            val cities = databaseProvider.dbQuery { City.selectAll() }
            cities.toCityDTOList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getById(id: String): CityDTO? {
       return try {
           val city = databaseProvider.dbQuery { City.select { City.id.eq(id) }.single() }
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
            cityId = this[City.id].value,
            nameEn = this[City.nameEn],
            nameRu = this[City.nameRu],
            descriptionEn = this[City.descriptionEn],
            descriptionRu = this[City.descriptionRu],
            imagesUrls = this[City.imagesUrls],
            population = this[City.population],
            latitude = this[City.latitude],
            longitude = this[City.longitude]
        )

}

object City : IdTable<String>("cities") {
    override val id: Column<EntityID<String>> = varchar("city_id", 60).entityId()

    val nameEn = varchar("name_en", 60)
    val nameRu = varchar("name_ru", 60)
    val descriptionEn = varchar("description_en", 60)
    val descriptionRu = varchar("description_ru", 60)
    val population = integer("population")
    val latitude = double("latitude")
    val longitude = double("longitude")
    val imagesUrls = stringList("images_urls")
    val countryId = integer("country_id")
}