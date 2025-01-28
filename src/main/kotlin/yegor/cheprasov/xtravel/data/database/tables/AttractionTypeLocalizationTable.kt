package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object AttractionTypeLocalizationTable : IdTable<Long>(name = "attraction_type_localization") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoIncrement()

    val languageCode = AttractionLocalizationTable.varchar("language_code", 10)
    val attractionId = AttractionLocalizationTable.reference("attraction_type", AttractionTypeTable.type)
    val name = varchar("name", 100)
    val description = text("description").nullable()
}