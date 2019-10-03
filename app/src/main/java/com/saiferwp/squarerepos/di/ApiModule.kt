package com.saiferwp.squarerepos.di

import com.saiferwp.squarerepos.data.api.ApiClient
import com.saiferwp.squarerepos.data.api.ApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApiClient(): ApiClient {
        val factory = ApiFactory()
        val api = factory.create()
        return ApiClient(api)
    }
}
