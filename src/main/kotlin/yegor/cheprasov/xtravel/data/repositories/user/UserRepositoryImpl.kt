package yegor.cheprasov.xtravel.data.repositories.user

import kotlinx.coroutines.Deferred
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO
import yegor.cheprasov.xtravel.data.database.tables.UsersTable
import java.util.UUID

class UserRepositoryImpl(private val databaseProvider: DatabaseProvider) : UserRepository {
    override suspend fun insert(userDTO: UserDTO): Unit =
        newSuspendedTransaction(db = databaseProvider.providedDatabase) {
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
        suspendedTransactionAsync(db = databaseProvider.providedDatabase) {
            try {
//            val user = UsersTable.select { UsersTable.login eq login }.singleOrNull()
                val user = databaseProvider.dbQuery { UsersTable.select { UsersTable.email eq email }.singleOrNull() }
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
            updatedAt = this[UsersTable.updatedAt].epochSeconds
        )
}