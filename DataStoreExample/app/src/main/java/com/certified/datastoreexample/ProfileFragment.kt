package com.certified.datastoreexample

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.certified.datastoreexample.databinding.DialogEditProfileBinding
import com.certified.datastoreexample.databinding.FragmentProfileBinding
import com.certified.datastoreexample.util.Extensions.dataStore
import com.certified.datastoreexample.util.Extensions.showToast
import com.certified.datastoreexample.util.PreferencesKeys.USER_EMAIL_PREF_KEY
import com.certified.datastoreexample.util.PreferencesKeys.USER_NAME_PREF_KEY
import com.certified.datastoreexample.util.PreferencesKeys.USER_PASSWORD_PREF_KEY
import com.certified.datastoreexample.util.PreferencesKeys.USER_PHONE_PREF_KEY
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding

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

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0) {
                groupEditName -> launchEditDetailDialog("name")
                groupEditEmail -> launchEditDetailDialog("email")
                groupEditPhone -> launchEditDetailDialog("phone")
                groupEditPassword -> launchEditDetailDialog("password")
            }
        }
    }

    private fun launchEditDetailDialog(which: String) {
        val view = DialogEditProfileBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogStyle)
        view.apply {
            when (which) {
                "name" -> {
                    tvEditProfileDialogTitle.text = "Edit name"
                    etEditProfileLayout.hint = "Name"
                    etEditProfile.setText(binding.tvName.text.toString())
                }
                "email" -> {
                    tvEditProfileDialogTitle.text = "Edit email"
                    etEditProfileLayout.hint = "Email"
                    etEditProfile.setText(binding.tvEmail.text.toString())
                }
                "phone" -> {
                    tvEditProfileDialogTitle.text = "Edit phone"
                    etEditProfileLayout.hint = "Phone"
                    etEditProfile.setText(binding.tvPhone.text.toString())
                }
                "password" -> {
                    tvEditProfileDialogTitle.text = "Edit password"
                    etEditProfileLayout.hint = "Password"
                    etEditProfile.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                    etEditProfile.setText(binding.tvPassword.text.toString())
                }
            }
            btnSave.setOnClickListener {
                val text = etEditProfile.text.toString().trim()
                if (text.isNotEmpty())
                    lifecycleScope.launch { saveUserDetails(text, which) }
                else
                    showToast("Field is required")
            }
            btnCancel.setOnClickListener { bottomSheetDialog.cancel() }
        }
        bottomSheetDialog.setContentView(view.root)
        bottomSheetDialog.show()
    }

    //     Write to Data Store
    private suspend fun saveUserDetails(text: String, which: String) {
        requireContext().apply {
            when (which) {
                "name" -> {
                    dataStore.edit {
                        it[USER_NAME_PREF_KEY] = text
                    }
                }
                "email" -> {
                    dataStore.edit {
                        it[USER_EMAIL_PREF_KEY] = text
                    }
                }
                "phone" -> {
                    dataStore.edit {
                        it[USER_PHONE_PREF_KEY] = text
                    }
                }
                "password" -> {
                    dataStore.edit {
                        it[USER_PASSWORD_PREF_KEY] = text
                    }
                }
            }
        }
    }
}