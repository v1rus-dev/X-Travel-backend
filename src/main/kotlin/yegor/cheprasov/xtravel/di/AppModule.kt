package yegor.cheprasov.xtravel.di

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.core.module.Module
import org.koin.dsl.module
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.data.repositories.user.UserRepositoryImpl

val appModule = module {

}

fun environmentModule(environment: ApplicationEnvironment): Module = module {
    single { environment }
}

fun databaseModule(database: Database): Module = module {
    single { database }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}