package com.uk.androidrecruitmentapp.factory

import com.uk.androidrecruitmentapp.RickyMortyRepository
import com.uk.androidrecruitmentapp.RestUtil
import com.uk.androidrecruitmentapp.data.remote.ApiService

object RepositoryFactory {
    fun createRMRepository() : RickyMortyRepository {
        val githubApi = RestUtil.instance.retrofit.create(ApiService::class.java)
        return RickyMortyRepository(githubApi)
    }
}