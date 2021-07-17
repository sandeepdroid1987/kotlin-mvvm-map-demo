
package com.sandeep.android.mapdemo.core.navigation

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.sandeep.android.mapdemo.features.countries.ui.map.CountriesActivity
import com.sandeep.android.mapdemo.features.countries.ui.detail.CountriesDetailActivity
import com.sandeep.android.mapdemo.features.countries.ui.model.CountryView
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showMain(context: Context) {
        showMainActivity(context)
    }

    private fun showMainActivity(context: Context) = context.startActivity(CountriesActivity.callingIntent(context))

    fun showDetailActivity(activity: FragmentActivity, countryView: CountryView) { //, navigationExtras: Extras) {
        val intent = CountriesDetailActivity.callingIntent(activity, countryView)
        activity.startActivity(intent)
       /* val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())*/
    }

    class Extras(val transitionSharedElement: View)
}