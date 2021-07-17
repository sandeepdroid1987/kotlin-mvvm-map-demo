package com.sandeep.android.mapdemo.features.countries.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/*"timezones": [
            "Europe/Rome"
        ],
        "latlng": [
            42.83333333,
            12.83333333
        ],
        "name": "Italy",
        "country_code": "IT",
        "capital": "Rome"*/

@Parcelize
data class CountryView (val latitude: Double, val longitude: Double, val name: String, val capital: String): Parcelable