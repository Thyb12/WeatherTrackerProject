package com.example.weathertrackerproject.directory

import android.content.Context
import com.example.weathertrackerproject.service.api.Api
import com.example.weathertrackerproject.service.repository.Repository
import com.example.weathertrackerproject.service.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
       api: Api,
       @ApplicationContext appContext: Context
    ): Repository = RepositoryImpl(api,appContext)
}