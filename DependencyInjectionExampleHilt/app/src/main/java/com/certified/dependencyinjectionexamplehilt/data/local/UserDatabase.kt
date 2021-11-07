package com.certified.dependencyinjectionexamplehilt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.certified.dependencyinjectionexamplehilt.data.model.User
import com.certified.dependencyinjectionexamplehilt.util.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val user = User(
                    "Click to set name",
                    "Click to set email",
                    "Click to set phone",
                    "Click to set password",
                    null
                )

                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.userDao()?.insertUser(user)
                }
            }
        }

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).addCallback(roomCallback).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return instance
        }
    }
}