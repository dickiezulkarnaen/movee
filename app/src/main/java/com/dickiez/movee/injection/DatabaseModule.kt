package com.dickiez.movee.injection

import android.content.Context
import androidx.room.Room
import com.dickiez.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {

  @Provides
  fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
    return Room.databaseBuilder(
      context,
      MovieDatabase::class.java,
      MovieDatabase.DB_NAME
    ).build()
  }
}