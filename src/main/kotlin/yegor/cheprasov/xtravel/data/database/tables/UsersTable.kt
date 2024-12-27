package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp
import java.util.*

object UsersTable : IdTable<UUID>(name = "users") {
    override val id: Column<EntityID<UUID>> = uuid("user_id").entityId()

    val login = varchar("login", length = 25)
    val password_hash = varchar("password_hash", length = 255)
    val email = varchar("email", length = 25)
    val name = varchar("name", length = 30)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
    val role = varchar("role", length = 10)
}