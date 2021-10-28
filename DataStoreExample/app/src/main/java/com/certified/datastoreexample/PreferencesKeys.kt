package com.certified.datastoreexample

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val USER_EMAIL_PREF_KEY: Preferences.Key<String> = stringPreferencesKey("USER_EMAIL_PREF_KEY")
    val USER_PHONE_PREF_KEY: Preferences.Key<String> = stringPreferencesKey("USER_PHONE_PREF_KEY")
    val USER_PASSWORD_PREF_KEY: Preferences.Key<String> =
        stringPreferencesKey("USER_PASSWORD_PREF_KEY")
    val USER_NAME_PREF_KEY: Preferences.Key<String> = stringPreferencesKey("USER_NAME_PREF_KEY")
}