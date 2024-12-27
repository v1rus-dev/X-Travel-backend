package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import java.util.UUID

object UpdateRoleRequestsTable : IdTable<UUID>("update_role_requests") {
    override val id: Column<EntityID<UUID>> = uuid("request_id").entityId()

    val userId = reference("user_id", UsersTable)
    val description = text("description")
    val instagramLink = varchar("instagram_link", 255).nullable()
    val facebookLink = varchar("facebook_link", 255).nullable()
    val xLink = varchar("x_link", 255).nullable()
    val confirmationCount = integer("confirmation_count")
    val state = varchar("state", 50).nullable()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}