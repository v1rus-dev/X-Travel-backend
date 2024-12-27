package yegor.cheprasov.xtravel.data.repositories.changeRole

import yegor.cheprasov.xtravel.data.database.DatabaseProvider

class ChangeRoleRepositoryImpl(
    private val databaseProvider: DatabaseProvider
) : ChangeRoleRepository {
    override suspend fun createChangeRoleRequest() {

    }
}