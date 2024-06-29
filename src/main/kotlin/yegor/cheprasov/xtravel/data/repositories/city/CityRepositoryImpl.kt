package yegor.cheprasov.xtravel.data.repositories.city

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.entities.cities.CityDTO

class CityRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : CityRepository {
    override suspend fun insert(cityDTO: CityDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<CityDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): CityDTO? {
        TODO("Not yet implemented")
    }


}

object City : IdTable<String>() {
    override val id: Column<EntityID<String>> = varchar("city_id", 60).entityId()

    val nameEn = varchar("name_en", 60)
    val nameRu = varchar("name_ru", 60)
    val descriptionEn = varchar("description_en", 60)
    val descriptionRu = varchar("description_ru", 60)
    val population = integer("population")
    val latitude = double("latitude")
    val longitude = double("longitude")
    val imagesUrls = text("images_urls")
}