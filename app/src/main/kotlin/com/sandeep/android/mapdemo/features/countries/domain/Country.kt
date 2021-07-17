package com.sandeep.android.mapdemo.features.countries.data


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

data class CountryResponse(val countryList: List<Country>)
data class Country (val timezones: List<String>, val latlng: List<Double>, val name: String, val country_code: String, val capital: String)