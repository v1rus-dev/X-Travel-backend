package yegor.cheprasov.xtravel.data.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object Users : Table() {
    private val id = Users.varchar("id", length = 50)
    private val login = Users.varchar("login", length = 25)
    private val password = Users.varchar("password", length = 25)
    private val firstName = Users.varchar("first_name", length = 30)
    private val lastName = Users.varchar("last_name", length = 30)
    private val email = Users.varchar("email", length = 30)
    private val userId = Users.varchar("user_id", length = 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[id] = UUID.randomUUID().toString()
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[firstName] = userDTO.firstName
                it[lastName] = userDTO.lastName
                it[email] = userDTO.email
                it[userId] = UUID.randomUUID().toString()
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            val userModel = Users.select { Users.login.eq(login) }.single()
            return UserDTO(
                userId = userModel[userId],
                login = userModel[Users.login],
                password = userModel[password],
                firstName = userModel[firstName],
                lastName = userModel[lastName],
                email = userModel[email]
            )
        } catch (e: Exception) {
            null
        }
    }
}