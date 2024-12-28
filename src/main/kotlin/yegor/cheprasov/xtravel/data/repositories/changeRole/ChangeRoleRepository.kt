package yegor.cheprasov.xtravel.data.repositories.changeRole

import kotlinx.coroutines.Deferred
import yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest.CreateRoleRequestDTO
import yegor.cheprasov.xtravel.data.database.dto.updateRoleRequest.UpdateRoleRequestSimpleDTO

interface ChangeRoleRepository {
    suspend fun createChangeRoleRequest(createRoleRequestDTO: CreateRoleRequestDTO): Deferred<String>
    suspend fun getSimpleChangeRoleRequestInfoByUserId(userId: String): Deferred<UpdateRoleRequestSimpleDTO?>
}