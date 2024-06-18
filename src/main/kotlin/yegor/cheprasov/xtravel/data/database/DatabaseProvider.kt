package yegor.cheprasov.xtravel.data.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent

class DatabaseProvider : DatabaseProviderContract, KoinComponent {

    private val dispatcher: CoroutineDispatcher = newFixedThreadPoolContext(5, "database-pool")
    private var database: Database? = null

    override fun init() {
        database = createDatabase()
    }

    override suspend fun <T> dbQuery(block: () -> T): T = withContext(dispatcher) {
        transaction { block() }
    }
}

interface DatabaseProviderContract {
    fun init()
    suspend fun <T> dbQuery(block: () -> T): T
}