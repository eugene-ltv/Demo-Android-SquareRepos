package com.saiferwp.squarerepos

import android.app.Application
import com.saiferwp.squarerepos.di.AppComponent
import com.saiferwp.squarerepos.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        component = DaggerAppComponent.builder()
//            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        lateinit var component: AppComponent private set
    }
}