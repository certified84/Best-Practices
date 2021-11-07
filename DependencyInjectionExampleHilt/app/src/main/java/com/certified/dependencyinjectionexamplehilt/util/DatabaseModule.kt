package com.certified.dependencyinjectionexamplehilt.util

import android.app.Application
import com.certified.dependencyinjectionexamplehilt.data.local.UserDao
import com.certified.dependencyinjectionexamplehilt.data.local.UserDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @ActivityScoped
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Application) =
        UserDatabase.getInstance(application)

    @Provides
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Provides
    fun provideRepository(userDao: UserDao) = Repository(userDao)
}