package yegor.cheprasov.xtravel.di

import org.koin.core.module.Module
import org.koin.dsl.module
import yegor.cheprasov.xtravel.data.database.DatabaseProvider
import yegor.cheprasov.xtravel.data.repositories.city.CityRepository
import yegor.cheprasov.xtravel.data.repositories.city.CityRepositoryImpl
import yegor.cheprasov.xtravel.data.repositories.city.CityRepositoryMock
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepository
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepositoryImpl
import yegor.cheprasov.xtravel.data.repositories.country.CountryRepositoryMock
import yegor.cheprasov.xtravel.data.repositories.user.UserRepository
import yegor.cheprasov.xtravel.data.repositories.user.UserRepositoryImpl
import yegor.cheprasov.xtravel.data.repositories.userProfile.UserProfileRepository
import yegor.cheprasov.xtravel.data.repositories.userProfile.UserProfileRepositoryImpl
import yegor.cheprasov.xtravel.utils.BuildConfig

fun databaseModule(): Module = module {
    single<DatabaseProvider> {
        DatabaseProvider().also {
            it.init()
        }
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<CountryRepository> {
        if (BuildConfig.useMockInformation) {
            CountryRepositoryMock()
        } else {
            CountryRepositoryImpl(get())
        }
    }

    single<UserProfileRepository> {
        UserProfileRepositoryImpl(get())
    }

    single<CityRepository> {
        if (BuildConfig.useMockInformation) {
            CityRepositoryMock()
        } else {
            CityRepositoryImpl(get())
        }
    }
}