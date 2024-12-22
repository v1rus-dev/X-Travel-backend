package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object CountryTable : IdTable<Long>(name = "countries") {
    override val id: Column<EntityID<Long>> = long("id").entityId()

    val nameRu = varchar("name_ru", 60)
    val nameEn = varchar("name_en", 60)
    val descriptionRu = text("description_ru")
    val descriptionEn = text("description_en")
    val population = integer("population").nullable()
    val latitude = double("latitude")
    val longitude = double("longitude")
    val folderName = varchar("folder_name", 30)
    val shortName = varchar("short_name", 30).nullable()

}