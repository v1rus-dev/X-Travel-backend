package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object AttractionInfoTable : IdTable<Long>(name = "attraction_info") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()

    val attractionId = reference("attraction_id", AttractionTable)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val rating = float("rating").nullable()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}