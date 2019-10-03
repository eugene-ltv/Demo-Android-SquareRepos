package com.saiferwp.squarerepos.di

import com.saiferwp.squarerepos.data.ReposProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        ReposModule::class
    ]
)
interface AppComponent {
    fun getReposProvider(): ReposProvider
}
