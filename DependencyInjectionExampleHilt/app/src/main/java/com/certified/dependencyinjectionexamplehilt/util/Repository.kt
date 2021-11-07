package com.certified.dependencyinjectionexamplehilt.util

import androidx.lifecycle.LiveData
import com.certified.dependencyinjectionexamplehilt.data.local.UserDao
import com.certified.dependencyinjectionexamplehilt.data.model.User
import javax.inject.Inject

class Repository @Inject constructor(private val userDao: UserDao) {

    //    private val userDao: UserDao = userDatabase.userDao()
    val user: LiveData<User> = userDao.getUser()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}