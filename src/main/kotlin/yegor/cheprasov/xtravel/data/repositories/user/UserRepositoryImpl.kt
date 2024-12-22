package yegor.cheprasov.xtravel.data.repositories.user

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO
import yegor.cheprasov.xtravel.data.repositories.user.Users.createdAt
import yegor.cheprasov.xtravel.data.repositories.user.Users.updatedAt
import java.util.UUID

class UserRepositoryImpl(private val databaseProvider: DatabaseProvider) : UserRepository {
    override suspend fun insert(userDTO: UserDTO) {
        databaseProvider.dbQuery {
            Users.insert {
                it[id] = userDTO.userId
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
                userId = userModel[Users.id].value,
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

object Users : IdTable<UUID>() {
    override val id: Column<EntityID<UUID>> = uuid("user_id").entityId()

    val login = varchar("login", length = 25)
    val password_hash = varchar("password_hash", length = 255)
    val email = varchar("email", length = 25)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
}