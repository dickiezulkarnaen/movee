package com.dickiez.movee.injection

import com.dickiez.data.api.TheMovieDatabaseApi
import com.dickiez.data.api.TraktApi
import com.dickiez.data.database.MovieDatabase
import com.dickiez.data.source.CreditRemoteDataSource
import com.dickiez.data.source.GenreLocalDataSource
import com.dickiez.data.source.GenreRemoteDataSource
import com.dickiez.data.source.MovieRemoteDataSource
import com.dickiez.movee.repository.CreditRepository
import com.dickiez.movee.repository.GenreRepository
import com.dickiez.movee.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [DatabaseModule::class])
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMovieRemoteDataSource(tmdbApi: TheMovieDatabaseApi, traktApi: TraktApi) : MovieRemoteDataSource {
    return MovieRemoteDataSource(tmdbApi, traktApi)
  }

  @Provides
  @ViewModelScoped
  fun provideGenreRemoteDataSource(tmdbApi: TheMovieDatabaseApi) : GenreRemoteDataSource {
    return GenreRemoteDataSource(tmdbApi)
  }

  @Provides
  @ViewModelScoped
  fun provideGenreLocalDataSource(movieDatabase: MovieDatabase) : GenreLocalDataSource {
    return GenreLocalDataSource(movieDatabase)
  }

  @Provides
  @ViewModelScoped
  fun provideCreditRemoteDataSource(tmdbApi: TheMovieDatabaseApi) : CreditRemoteDataSource {
    return CreditRemoteDataSource(tmdbApi)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(dataSource: MovieRemoteDataSource) : MovieRepository {
    return MovieRepository(dataSource)
  }

  @Provides
  @ViewModelScoped
  fun provideGenreRepository(
    remoteDataSource: GenreRemoteDataSource,
    localDataSource: GenreLocalDataSource,
  ) : GenreRepository {
    return GenreRepository(remoteDataSource, localDataSource)
  }

  @Provides
  @ViewModelScoped
  fun provideCreditRepository(
    remoteDataSource: CreditRemoteDataSource,
  ) : CreditRepository {
    return CreditRepository(remoteDataSource)
  }

}