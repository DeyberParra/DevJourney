package com.app.countries.di

import android.content.Context
import com.app.core.di.CountriesRetrofit
import com.app.countries.data.datasource.CountryLocalDataSource
import com.app.countries.data.repository.CountryRepository
import com.app.countries.data.repository.CountryRepositoryImpl
import com.app.countries.data.service.CountryService
import com.app.countries.domain.useCases.GetAllCountriesUseCases
import com.app.countries.domain.useCases.GetDetailCountryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountriesModule  {


    @Provides
    @Singleton
    fun providesDataSource(
        @ApplicationContext context : Context
    ) : CountryLocalDataSource {
        return CountryLocalDataSource(context)
    }
    @Provides
    @Singleton
    fun provideCountryRepository(
        dataSource: CountryLocalDataSource,
        countryService: CountryService
    ) : CountryRepository {
        return CountryRepositoryImpl(dataSource, countryService)
    }

    @Provides
    @Singleton
    fun provideGetAllCountriesUseCase(
        repository: CountryRepository
    ) : GetAllCountriesUseCases{
        return GetAllCountriesUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideGetDetailCountriesUseCase(
        repository: CountryRepository
    ) : GetDetailCountryUseCases{
        return GetDetailCountryUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideCountryService(
        @CountriesRetrofit retrofit: Retrofit
    ) : CountryService{
        return retrofit.create(CountryService::class.java)
    }

}