package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import yegor.cheprasov.xtravel.data.database.tables.AttractionTable.autoIncrement
import yegor.cheprasov.xtravel.data.database.tables.AttractionTable.entityId

object AttractionLocalizationTable : IdTable<Long>(name = "attraction_localization") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()

    val attractionId = reference("attraction_id", AttractionTable)
    val languageCode = varchar("language_code", 10)
    val name = varchar("name", 100)
    val description = text("description")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}