package yegor.cheprasov.xtravel.data.repositories.user

import kotlinx.coroutines.Deferred
import kotlinx.datetime.Instant
import org.h2.engine.User
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO
import yegor.cheprasov.xtravel.data.database.tables.UsersTable
import yegor.cheprasov.xtravel.entities.enums.UserRole
import java.util.UUID

class UserRepositoryImpl(private val databaseProvider: DatabaseProvider) : UserRepository {
    override suspend fun insert(userDTO: UserDTO): Unit =
        newSuspendedTransaction {
            databaseProvider.dbQuery {
                UsersTable.insert {
                    it[id] = UUID.randomUUID()
                    it[login] = userDTO.login
                    it[password_hash] = userDTO.passwordHash
                    it[email] = userDTO.email
                    it[name] = userDTO.name
                    it[createdAt] = Instant.fromEpochMilliseconds(userDTO.createdAt)
                    it[updatedAt] = Instant.fromEpochMilliseconds(userDTO.updatedAt)
                }
            }
        }

    override suspend fun fetchUser(email: String): Deferred<UserDTO?> =
        suspendedTransactionAsync {
            return@suspendedTransactionAsync try {
                (UsersTable)
                    .selectAll()
                    .where { UsersTable.email.eq(email) }
                    .singleOrNull()?.mapToUserDTO()
            } catch (e: Exception) {
                e.printStackTrace()
                return@suspendedTransactionAsync null
            }
        }

    override suspend fun fetchUserById(id: String): Deferred<UserDTO?> =
        suspendedTransactionAsync {
            try {
                val user = databaseProvider.dbQuery {
                    UsersTable.selectAll()
                        .where(UsersTable.id.eq(EntityID(id = UUID.fromString(id), table = UsersTable)))
                }.singleOrNull()
                return@suspendedTransactionAsync user?.mapToUserDTO()
            } catch (e: Exception) {
                e.printStackTrace()
                return@suspendedTransactionAsync null
            }
        }

    private fun ResultRow.mapToUserDTO(): UserDTO =
        UserDTO(
            userId = this[UsersTable.id].value,
            login = this[UsersTable.login],
            passwordHash = this[UsersTable.password_hash],
            email = this[UsersTable.email],
            name = this[UsersTable.name],
            createdAt = this[UsersTable.createdAt].epochSeconds,
            updatedAt = this[UsersTable.updatedAt].epochSeconds,
            role = UserRole.getById(this[UsersTable.role]) ?: UserRole.Default
        )
}