package yegor.cheprasov.xtravel.data.repositories.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.entities.users.UserDTO
import yegor.cheprasov.xtravel.data.repositories.user.Users.createdAt
import yegor.cheprasov.xtravel.data.repositories.user.Users.updatedAt

class UserRepositoryImpl(private val databaseProvider: DatabaseProvider) : UserRepository {
    override suspend fun insert(userDTO: UserDTO) {
        databaseProvider.dbQuery {
            Users.insert {
                it[userId] = userDTO.userId
                it[login] = userDTO.login
                it[password_hash] = userDTO.passwordHash
                it[email] = userDTO.email
                it[createdAt] = userDTO.createdAt
                it[updatedAt] = userDTO.updatedAt
            }
        }
    }

    override suspend fun fetchUser(login: String): UserDTO? {
        return try {
            val userModel = databaseProvider.dbQuery { Users.select { Users.login.eq(login) }.single() }
            UserDTO(
                userId = userModel[Users.userId],
                login = userModel[Users.login],
                passwordHash = userModel[Users.password_hash],
                email = userModel[Users.email],
                createdAt = userModel[createdAt],
                updatedAt = userModel[updatedAt]
            )
        } catch (e: Exception) {
            null
        }
    }
}

object Users : IntIdTable() {
    val userId = uuid("user_id")
    val login = varchar("login", length = 25)
    val password_hash = varchar("password_hash", length = 255)
    val email = varchar("email", length = 25)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
}