package com.dickiez.movee.injection

import com.dickiez.core.constants.ApiConstants
import com.dickiez.data.api.ApiService
import com.dickiez.data.api.TheMovieDatabaseApi
import com.dickiez.data.api.TraktApi
import com.dickiez.data.api.client.TheMovieDatabaseApiClient
import com.dickiez.data.api.client.TraktApiClient
import com.dickiez.movee.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

  @Singleton
  @Provides
  fun provideTheMovieDatabaseApi(apiClient: TheMovieDatabaseApiClient) : TheMovieDatabaseApi {
    return ApiService.getRetrofit(ApiConstants.TMDB.BASE_URL, apiClient)
  }

  @Singleton
  @Provides
  fun provideTheMovieDatabaseApiClient() : TheMovieDatabaseApiClient {
    return TheMovieDatabaseApiClient()
  }
  @Singleton
  @Provides
  fun provideTraktApi(apiClient: TraktApiClient) : TraktApi {
    return ApiService.getRetrofit(ApiConstants.Trakt.BASE_URL, apiClient)
  }

  @Singleton
  @Provides
  fun provideTraktApiClient() : TraktApiClient {
    return TraktApiClient()
  }



}