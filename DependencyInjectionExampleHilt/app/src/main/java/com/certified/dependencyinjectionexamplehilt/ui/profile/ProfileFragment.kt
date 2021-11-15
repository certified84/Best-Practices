package com.certified.dependencyinjectionexamplehilt.ui.profile

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.certified.datastoreexample.util.Extensions.showToast
import com.certified.dependencyinjectionexamplehilt.R
import com.certified.dependencyinjectionexamplehilt.databinding.DialogEditProfileBinding
import com.certified.dependencyinjectionexamplehilt.databinding.FragmentProfileBinding
import com.certified.dependencyinjectionexamplehilt.util.Repository
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.apply {
            groupEditName.setOnClickListener { launchEditDetailDialog("name") }
            groupEditEmail.setOnClickListener { launchEditDetailDialog("email") }
            groupEditPhone.setOnClickListener { launchEditDetailDialog("phone") }
            groupEditPassword.setOnClickListener { launchEditDetailDialog("password") }
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
            btnUpdate.setOnClickListener {
                val text = etEditProfile.text.toString().trim()
                if (text.isNotEmpty()) {
                    lifecycleScope.launch { saveUserDetails(text, which) }
                    bottomSheetDialog.dismiss()
                } else
                    showToast("Field is required")
            }
            btnCancel.setOnClickListener { bottomSheetDialog.cancel() }
        }
        bottomSheetDialog.setContentView(view.root)
        bottomSheetDialog.show()
    }

    //     Write to room
    private fun saveUserDetails(text: String, which: String) {
        viewModel.user.observe(viewLifecycleOwner) {
            when (which) {
                "name" -> {
                    it.name = text
                    viewModel.updateUser(it)
                }
                "email" -> {
                    it.email = text
                    viewModel.updateUser(it)
                }
                "phone" -> {
                    it.phone = text
                    viewModel.updateUser(it)
                }
                "password" -> {
                    it.password = text
                    viewModel.updateUser(it)
                }
            }
        }
    }
}