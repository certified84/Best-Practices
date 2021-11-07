package com.certified.dependencyinjectionexamplehilt.view.profile

import androidx.lifecycle.*
import com.certified.dependencyinjectionexamplehilt.data.model.User
import com.certified.dependencyinjectionexamplehilt.util.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        getUser()
    }

    private fun getUser() {
        _user.value = repository.user.value
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}