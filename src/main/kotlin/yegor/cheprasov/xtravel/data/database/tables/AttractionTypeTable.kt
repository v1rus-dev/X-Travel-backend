package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object AttractionTypeTable : Table(name = "attraction_types") {
    val type: Column<EntityID<String>> = varchar("type", 36).entityId()
    val nameRu = varchar("name_ru", 100)
    val nameEn = varchar("name_en", 100)
    val descriptionRu = text("description_ru").nullable()
    val descriptionEn = text("description_en").nullable()
}