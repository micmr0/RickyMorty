package com.uk.androidrecruitmentapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uk.androidrecruitmentapp.RickyMortyRepository
import com.uk.androidrecruitmentapp.ui.main.MainActivityViewModel

class ViewModelFactory(private val rickyMortyRepository: RickyMortyRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(rickyMortyRepository) as T
    }

}