package com.e.tubesmobile.di

import android.app.Application
import androidx.room.Room
import com.e.tubesmobile.persistance.AppDatabase
import com.e.tubesmobile.persistance.KomputerDao
import com.e.tubesmobile.persistance.PeriferalDao
import com.e.tubesmobile.persistance.SmarthphoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "pengelolaan-komputer"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideKomputerDao(appDatabase: AppDatabase): KomputerDao {
        return appDatabase.komputerDao()
    }

    @Provides
    @Singleton
    fun providePeriferalDao(appDatabase: AppDatabase): PeriferalDao {
        return appDatabase.periferalDao()
    }

    @Provides
    @Singleton
    fun provideSmarthphoneDao(appDatabase: AppDatabase): SmarthphoneDao {
        return appDatabase.smarthphoneDao()
    }
}