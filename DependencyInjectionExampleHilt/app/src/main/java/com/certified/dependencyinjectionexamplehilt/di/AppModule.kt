package com.certified.dependencyinjectionexamplehilt.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ActivityRetainedComponent::class)
@Module
class AppModule {

    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context
    }
}