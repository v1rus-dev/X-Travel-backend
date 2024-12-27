package yegor.cheprasov.xtravel.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object RoleUpdateVotesTable : IntIdTable("role_update_votes") {
    val userId = reference("user_id", UsersTable)
    val requestId = reference("request_id", UpdateRoleRequestsTable)
}