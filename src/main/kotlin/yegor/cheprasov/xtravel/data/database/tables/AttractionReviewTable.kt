package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import yegor.cheprasov.xtravel.data.database.tables.AttractionTable.autoIncrement
import yegor.cheprasov.xtravel.data.database.tables.AttractionTable.entityId

object AttractionReviewTable : IdTable<Long>("attraction_reviews") {
    override val id: Column<EntityID<Long>> = long("id").autoIncrement().entityId()

    val attractionId = reference("attraction_id", AttractionTable)
    val userId = reference("user_id", UsersTable).nullable()
    val rating = float("rating")
    val reviewText = text("review_text").nullable()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}