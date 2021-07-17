
package com.sandeep.android.mapdemo

import android.app.Application
import com.sandeep.android.mapdemo.core.di.ApplicationComponent
import com.sandeep.android.mapdemo.core.di.ApplicationModule
import com.sandeep.android.mapdemo.core.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class MapDemoApp : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }

    private fun injectMembers() = appComponent.inject(this)

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
