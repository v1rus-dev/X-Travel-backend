package yegor.cheprasov.xtravel.data.database.users

data class UserDTO(
    val userId: String? = null,
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val email: String
)