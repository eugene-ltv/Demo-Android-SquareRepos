package com.saiferwp.squarerepos.di

import com.saiferwp.squarerepos.data.ReposProvider
import com.saiferwp.squarerepos.data.api.ApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class
    ]
)
open class ReposModule {
    @Provides
    @Singleton
    open fun provideRepos(
        apiClient: ApiClient
    ) = ReposProvider(apiClient)
}