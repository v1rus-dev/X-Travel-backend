package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CityTable : IdTable<Long>(name = "cities") {
    override val id: Column<EntityID<Long>> = long("id").entityId()

    val nameRu = varchar("name_ru", 60)
    val nameEn = varchar("name_en", 60)
    val descriptionRu = text("description_ru")
    val descriptionEn = text("description_en")
    val population = integer("population").nullable()
    val countryId = reference("country_id", CountryTable)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val folderName = varchar("folder_name", 30)
}