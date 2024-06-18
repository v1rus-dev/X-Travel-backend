package yegor.cheprasov.xtravel.di

import org.koin.dsl.module
import yegor.cheprasov.xtravel.security.JwtConfig

val jwtConfigModule = module {
    single {
        JwtConfig(get())
    }
}