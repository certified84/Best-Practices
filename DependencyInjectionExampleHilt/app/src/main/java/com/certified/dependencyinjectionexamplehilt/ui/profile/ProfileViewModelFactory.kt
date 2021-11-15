package com.certified.dependencyinjectionexamplehilt.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.certified.dependencyinjectionexamplehilt.util.Repository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ProfileViewModelFactory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}