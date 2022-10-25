package com.example.weathertrackerproject.directory

import android.content.Context
import com.example.weathertrackerproject.data.api.Api
import com.example.weathertrackerproject.domain.repository.Repository
import com.example.weathertrackerproject.data.repository.RepositoryImpl
import com.example.weathertrackerproject.domain.UseCase.UpdateWeatherData
import com.example.weathertrackerproject.domain.UseCase.UpdateWeatherDataUseCase
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
    ): Repository = RepositoryImpl(api, appContext)

    @Provides
    @Singleton
    fun provideUpdateWeatherDataUseCase(repository: Repository): UpdateWeatherDataUseCase {
        return UpdateWeatherDataUseCase(updateWeatherData = UpdateWeatherData(repository))
    }
}