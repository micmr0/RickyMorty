package com.uk.androidrecruitmentapp

import android.app.Application
import com.uk.androidrecruitmentapp.di.ApiComponent
import com.uk.androidrecruitmentapp.di.ApiModule
import com.uk.androidrecruitmentapp.di.DaggerApiComponent

class ARApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .build()
    }

    companion object {
        lateinit var apiComponent: ApiComponent
    }
}