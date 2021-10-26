/*
 * Copyright (c) 2021 Samson Achiaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.certified.restpractice.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.certified.restpractice.Network.UserApiService
import com.certified.restpractice.model.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _showProgressBar = MutableLiveData(true)
    val showProgressBar: LiveData<Boolean>
        get() = _showProgressBar

    private val _showToast = MutableLiveData(true)
    val showToast: LiveData<Boolean>
        get() = _showToast

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val getUsers = UserApiService.createUserApiService().getUsers()
                _users.value = getUsers.data
                if (users.value?.isNotEmpty() == true)
                    _showProgressBar.value = false
            } catch (e: Exception) {
                _showToast.value = true
            }
        }
    }
}