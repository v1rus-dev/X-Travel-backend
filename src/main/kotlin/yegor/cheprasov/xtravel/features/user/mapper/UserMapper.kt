package yegor.cheprasov.xtravel.features.user.mapper

import yegor.cheprasov.xtravel.data.database.dto.users.UserDTO
import yegor.cheprasov.xtravel.features.user.UserInfoResponse

object UserMapper {
    fun mapToUserInfo(userDTO: UserDTO): UserInfoResponse = UserInfoResponse(
        userId = userDTO.userId.toString(),
        name = userDTO.name,
        email = userDTO.email,
        role = userDTO.role.id
    )
}