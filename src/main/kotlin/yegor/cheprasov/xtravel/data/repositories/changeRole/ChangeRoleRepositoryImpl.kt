package yegor.cheprasov.xtravel.data.repositories.changeRole

import kotlinx.coroutines.Deferred
import kotlinx.datetime.Instant
import org.h2.engine.User
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest.CreateRoleRequestDTO
import yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest.UpdateRoleRequestSimpleDTO
import yegor.cheprasov.xtravel.data.database.tables.UpdateRoleRequestsTable
import yegor.cheprasov.xtravel.data.database.tables.UsersTable
import yegor.cheprasov.xtravel.entities.enums.UpdateRoleRequestState
import java.util.UUID

class ChangeRoleRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : ChangeRoleRepository {
    override suspend fun createChangeRoleRequest(createRoleRequestDTO: CreateRoleRequestDTO): Deferred<String> =
        suspendedTransactionAsync(db = databaseProvider.providedDatabase) {
            val requestId = UUID.randomUUID()

            databaseProvider.dbQuery {
                UpdateRoleRequestsTable.insert {
                    it[id] = requestId
                    it[userId] = UUID.fromString(createRoleRequestDTO.userId)
                    it[description] = createRoleRequestDTO.description
                    it[instagramLink] = createRoleRequestDTO.instagramLink
                    it[facebookLink] = createRoleRequestDTO.facebookLink
                    it[xLink] = createRoleRequestDTO.xLink
                    it[confirmationCount] = 0
                    it[state] = UpdateRoleRequestState.Pending.id
                    it[createdAt] = Instant.fromEpochMilliseconds(createRoleRequestDTO.createdAt)
                    it[updatedAt] = Instant.fromEpochMilliseconds(createRoleRequestDTO.updatedAt)
                }

                requestId.toString()
            }
        }

    override suspend fun getSimpleChangeRoleRequestInfoByUserId(userId: String): Deferred<UpdateRoleRequestSimpleDTO?> =
        suspendedTransactionAsync(db = databaseProvider.providedDatabase) {
            try {
                val user = databaseProvider.dbQuery {
                    UpdateRoleRequestsTable.select(UpdateRoleRequestsTable.userId)
                        .where(
                            UpdateRoleRequestsTable.userId.eq(
                                EntityID(
                                    id = UUID.fromString(userId),
                                    table = UsersTable
                                )
                            )
                        )
                }.singleOrNull()
                return@suspendedTransactionAsync user?.toUpdateRoleRequestSimpleDTO()
            } catch (e: Exception) {
                e.printStackTrace()
                return@suspendedTransactionAsync null
            }
        }

    private fun ResultRow.toUpdateRoleRequestSimpleDTO() = UpdateRoleRequestSimpleDTO(
        requestId = this[UpdateRoleRequestsTable.id].value,
        userId = this[UpdateRoleRequestsTable.userId].value,
        confirmationCount = this[UpdateRoleRequestsTable.confirmationCount],
        state = UpdateRoleRequestState.getById(
            this[UpdateRoleRequestsTable.state] ?: throw IllegalArgumentException("State cannot be null")
        ),
    )
}