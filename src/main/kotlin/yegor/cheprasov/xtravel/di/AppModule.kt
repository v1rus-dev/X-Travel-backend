package yegor.cheprasov.xtravel.di

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {

}

fun environmentModule(environment: ApplicationEnvironment): Module = module {
    single { environment }
}

