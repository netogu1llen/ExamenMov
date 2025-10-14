package com.app.examen.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.app.examen.data.local.model.CountryCache
import com.app.examen.domain.model.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryPreferences
    @Inject
    constructor(
        @ApplicationContext context: Context,
        private val gson: Gson,
    ) {
        private val prefs: SharedPreferences =
            context.getSharedPreferences(
                PreferencesConstants.PREF_NAME,
                Context.MODE_PRIVATE,
            )

        fun saveCountryList(countryList: List<Country>) {
            prefs
                .edit()
                .putString(PreferencesConstants.KEY_COUNTRY_CACHE, gson.toJson(countryList))
                .putLong(PreferencesConstants.KEY_LAST_UPDATE, System.currentTimeMillis())
                .apply()
        }

        fun getCountryCache(): CountryCache? {
            val json = prefs.getString(PreferencesConstants.KEY_COUNTRY_CACHE, null)
            val lastUpdate = prefs.getLong(PreferencesConstants.KEY_LAST_UPDATE, 0)

            if (json == null) return null

            val type = object : TypeToken<List<Country>>() {}.type
            val countryList: List<Country> = gson.fromJson(json, type)

            return CountryCache(
                countryList = countryList,
                lastUpdate = lastUpdate,
            )
        }

        fun isCacheValid(): Boolean {
            val lastUpdate = prefs.getLong(PreferencesConstants.KEY_LAST_UPDATE, 0)
            return System.currentTimeMillis() - lastUpdate < PreferencesConstants.CACHE_DURATION
        }

        fun clearCache() {
            prefs.edit().clear().apply()
        }
    }
