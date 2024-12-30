package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object AttractionTable : IdTable<Long>(name = "attractions") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()

    val nameRu = varchar("name_ru", 100)
    val nameEn = varchar("name_en", 100)
    val descriptionRu = text("description_ru")
    val descriptionEn = text("description_en")
    val latitude = double("latitude")
    val longitude = double("longitude")
    val rating = float("rating").nullable()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
    val cityId = reference("city_id", CityTable).nullable()
    val attractionType = reference("attraction_type", AttractionTypeTable.type)
    val folderName = varchar("folder_name", 30)
}