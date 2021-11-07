package com.certified.dependencyinjectionexamplehilt.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.certified.dependencyinjectionexamplehilt.data.model.User
import kotlinx.coroutines.flow.SharedFlow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table WHERE id = 0")
    fun getUser(): LiveData<User>
}