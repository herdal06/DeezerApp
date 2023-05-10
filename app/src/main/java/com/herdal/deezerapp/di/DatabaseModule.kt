package com.herdal.deezerapp.di

import android.content.Context
import androidx.room.Room
import com.herdal.deezerapp.data.local.DeezerDatabase
import com.herdal.deezerapp.utils.constants.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DeezerDatabase::class.java,
        DatabaseConstants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideTrackDao(db: DeezerDatabase) = db.trackDao()
}