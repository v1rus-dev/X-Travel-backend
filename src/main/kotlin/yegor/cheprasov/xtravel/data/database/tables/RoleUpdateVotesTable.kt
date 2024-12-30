package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object RoleUpdateVotesTable : IdTable<Long>("role_update_votes") {

    override val id: Column<EntityID<Long>> = long("id").entityId().autoinc()

    val userId = reference("user_id", UsersTable)
    val requestId = reference("request_id", UpdateRoleRequestsTable)
}