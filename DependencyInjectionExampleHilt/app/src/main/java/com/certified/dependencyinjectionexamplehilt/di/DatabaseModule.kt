package com.certified.dependencyinjectionexamplehilt.di

import android.content.Context
import com.certified.dependencyinjectionexamplehilt.data.local.UserDao
import com.certified.dependencyinjectionexamplehilt.data.local.UserDatabase
import com.certified.dependencyinjectionexamplehilt.ui.profile.ProfileViewModelFactory
import com.certified.dependencyinjectionexamplehilt.util.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    //    @ActivityScoped
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        UserDatabase.getInstance(context)

    @Provides
    fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Provides
    fun provideRepository(userDao: UserDao) = Repository(userDao)

    @Provides
    fun provideViewModelFactory(repository: Repository) = ProfileViewModelFactory(repository)
}