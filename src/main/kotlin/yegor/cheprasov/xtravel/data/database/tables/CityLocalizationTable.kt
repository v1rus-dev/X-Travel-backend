package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CityLocalizationTable : IdTable<Long>("city_localization") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()
    val cityId = reference("city_id", CountryTable)
    val languageCode = varchar("language_code", 10)
    val name = varchar("name", 60)
    val description = text("description")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}