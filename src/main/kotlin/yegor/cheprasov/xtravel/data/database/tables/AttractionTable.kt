package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object AttractionTable : IdTable<Long>(name = "attractions") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()

    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
    val cityId = reference("city_id", CityTable).nullable()
    val attractionType = reference("attraction_type", AttractionTypeTable.type)
    val folderName = varchar("folder_name", 30)
    val internalName = varchar("internal_name", 30)
    val countryId = reference("country_id", CountryTable)
}