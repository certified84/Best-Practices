package com.certified.datastoreexample.util

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment

object Extensions {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "com.certified.datastoreexample.userdetails")
    fun Fragment.showToast(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}