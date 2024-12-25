package yegor.cheprasov.xtravel.di

import io.ktor.server.application.*
import org.koin.core.module.Module
import org.koin.dsl.module
import yegor.cheprasov.xtravel.utils.FileService

val appModule = module {
    single { FileService(baseDirectory = "files") }
}

fun environmentModule(environment: ApplicationEnvironment): Module = module {
    single { environment }
}

