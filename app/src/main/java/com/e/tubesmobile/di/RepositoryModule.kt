package com.e.tubesmobile.di

import com.e.tubesmobile.network.KomputerApi
import com.e.tubesmobile.network.PeriferalApi
import com.e.tubesmobile.network.SmarthphoneApi
import com.e.tubesmobile.persistance.KomputerDao
import com.e.tubesmobile.persistance.PeriferalDao
import com.e.tubesmobile.persistance.SmarthphoneDao
import com.e.tubesmobile.repositories.KomputerRepository
import com.e.tubesmobile.repositories.PeriferalRepository
import com.e.tubesmobile.repositories.SmarthphoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideKomputerRepository(
        api: KomputerApi,
        dao: KomputerDao
    ): KomputerRepository {
        return KomputerRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun providePeriferalRepository(
        api: PeriferalApi,
        dao: PeriferalDao
    ): PeriferalRepository {
        return PeriferalRepository(api, dao)
    }

    @Provides
    @ViewModelScoped
    fun provideSmarthphoneRepository(
        api: SmarthphoneApi,
        dao: SmarthphoneDao
    ): SmarthphoneRepository {
        return SmarthphoneRepository(api, dao)
    }
}