
package com.sandeep.android.mapdemo.core.navigation

import com.sandeep.android.mapdemo.AndroidTest
import com.sandeep.android.mapdemo.features.countries.ui.map.CountriesActivity
import com.sandeep.android.mapdemo.shouldNavigateTo
import org.junit.Before
import org.junit.Test


class NavigatorTest : AndroidTest() {

    private lateinit var navigator: Navigator

    @Before fun setup() {
        navigator = Navigator()
    }

    @Test fun `should forward user to characters screen`() {
        navigator.showMain(activityContext())
        RouteActivity::class shouldNavigateTo CountriesActivity::class
    }
}
