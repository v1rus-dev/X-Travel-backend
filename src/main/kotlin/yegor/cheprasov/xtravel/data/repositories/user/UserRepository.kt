package yegor.cheprasov.xtravel.data.repositories.user

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO

interface UserRepository {
    suspend fun insert(userDTO: UserDTO)
    suspend fun fetchUser(email: String): Deferred<UserDTO?>
    suspend fun fetchUserById(id: String): Deferred<UserDTO?>
}