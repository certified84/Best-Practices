package com.certified.dependencyinjectionexamplehilt.util

import android.app.Application
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface AppModule {

    @Binds
    fun bindApplication(application: MyApplication): Application
}