
package com.sandeep.android.mapdemo.core.di

import com.sandeep.android.mapdemo.MapDemoApp
import com.sandeep.android.mapdemo.core.di.viewmodel.ViewModelModule
import com.sandeep.android.mapdemo.core.navigation.RouteActivity
import com.sandeep.android.mapdemo.features.countries.ui.detail.CountriesDetailActivity
import com.sandeep.android.mapdemo.features.countries.ui.map.CountriesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: MapDemoApp)
    fun inject(routeActivity: RouteActivity)
    fun inject(countriesActivity: CountriesActivity)
    fun inject(countriesActivity: CountriesDetailActivity)

}
