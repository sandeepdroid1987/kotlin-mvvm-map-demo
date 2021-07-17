package com.sandeep.android.mapdemo.core.di

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.sandeep.android.mapdemo.MapDemoApp
import com.sandeep.android.mapdemo.features.countries.domain.CountriesRepository
import com.sandeep.android.mapdemo.features.countries.data.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MapDemoApp) {

    @Provides @Singleton fun provideApplicationContext(): Context = application

    @Provides @Singleton fun provideResources(application: Context): Resources = application.resources

    @Provides @Singleton fun provideGson(): Gson = Gson()

    @Provides @Singleton fun provideCountriesRepository(countriesRepositoryImpl: CountriesRepositoryImpl): CountriesRepository = countriesRepositoryImpl

}
