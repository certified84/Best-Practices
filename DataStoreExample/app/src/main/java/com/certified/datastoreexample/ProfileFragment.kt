package com.certified.datastoreexample

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.certified.datastoreexample.PreferencesKeys.USER_EMAIL_PREF_KEY
import com.certified.datastoreexample.PreferencesKeys.USER_NAME_PREF_KEY
import com.certified.datastoreexample.PreferencesKeys.USER_PASSWORD_PREF_KEY
import com.certified.datastoreexample.PreferencesKeys.USER_PHONE_PREF_KEY
import com.certified.datastoreexample.databinding.FragmentProfileBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "com.certified.datastoreexample.userdetails")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            loadUserDetails()
        }

        return binding.root
    }

    //    Read from data store
    private suspend fun loadUserDetails() {

        val userNameFlow: Flow<String> = requireContext().dataStore.data
            .map {
                it[USER_NAME_PREF_KEY] ?: "Click to set name"
            }

        val userEmailFlow: Flow<String> = requireContext().dataStore.data.map {
            it[USER_EMAIL_PREF_KEY] ?: "Click to set email"
        }

        val userPhoneFlow: Flow<String> = requireContext().dataStore.data.map {
            it[USER_PHONE_PREF_KEY] ?: "Click to set phone"
        }

        val userPasswordFlow: Flow<String> = requireContext().dataStore.data.map {
            it[USER_PASSWORD_PREF_KEY] ?: "Click to set password"
        }

        binding.apply {
            userNameFlow.collect {
                tvName.text = it
            }

            userEmailFlow.collect {
                tvEmail.text = it
            }

            userPhoneFlow.collect {
                tvPhone.text = it
            }

            userPasswordFlow.collect {
                tvPassword.text = it
            }
        }
    }

//     Write to Data Store
    private suspend fun saveUserDetails() {
        val highestScore = "12"
        lifecycleScope.launch {
            requireContext().dataStore.edit { appDataStore ->
                appDataStore[USER_NAME_PREF_KEY] = highestScore
            }
        }
    }
}