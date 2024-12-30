package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object CityReviewTable : IdTable<Long>(name = "city_reviews") {
    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()

    val cityId = reference("city_id", CityTable)
    val userId = reference("user_id", UsersTable).nullable()
    val rating = float("rating")
    val reviewText = text("review_text").nullable()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")

}