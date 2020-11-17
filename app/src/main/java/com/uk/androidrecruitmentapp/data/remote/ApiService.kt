package com.uk.androidrecruitmentapp.data.remote

import com.uk.androidrecruitmentapp.data.local.Character
import com.uk.androidrecruitmentapp.data.local.Episode
import com.uk.androidrecruitmentapp.data.local.Location
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("episode/")
    fun getEpisodes(): Single<Response<Episode>>
    @GET("character/")
    fun getCharacters(): Single<Response<Character>>
    @GET("location/")
    fun getLocations(): Single<Response<Location>>
}