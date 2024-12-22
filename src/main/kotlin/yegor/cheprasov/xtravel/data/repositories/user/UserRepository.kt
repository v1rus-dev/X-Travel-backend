package yegor.cheprasov.xtravel.data.repositories.user

import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO

interface UserRepository {
    suspend fun insert(userDTO: UserDTO)
    suspend fun fetchUser(login: String): UserDTO?
}