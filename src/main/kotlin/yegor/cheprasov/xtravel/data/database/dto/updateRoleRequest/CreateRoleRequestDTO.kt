package yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest

data class CreateRoleRequestDTO(
    val userId: String,
    val description: String,
    val instagramLink: String?,
    val facebookLink: String?,
    val xLink: String?,
    val createdAt: Long,
    val updatedAt: Long
)