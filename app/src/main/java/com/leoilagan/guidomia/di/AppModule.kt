package com.leoilagan.guidomia.di

import android.content.Context
import com.leoilagan.guidomia.data.db.AppDatabase
import com.leoilagan.guidomia.data.repositories.CarRepository
import com.leoilagan.guidomia.data.repositories.CarRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * all dependencies of entire application goes here
 */

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideCarRepository(context: Context, appDatabase: AppDatabase) : CarRepository {
        return CarRepositoryImpl(context,appDatabase)
    }

    /**
     * providing room database dao for accessing data in database
     */
    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context) : AppDatabase {
        return AppDatabase.getInstance(context)
    }
}