package com.uk.androidrecruitmentapp.di

import com.uk.androidrecruitmentapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(activity: MainActivity)
}