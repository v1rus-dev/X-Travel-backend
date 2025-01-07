package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CityInfoTable : IdTable<Long>(name = "city_info") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()

    val cityId = reference("city_id", CityTable)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}