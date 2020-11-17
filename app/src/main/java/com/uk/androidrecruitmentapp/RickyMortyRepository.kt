package com.uk.androidrecruitmentapp

import android.util.Log
import com.uk.androidrecruitmentapp.data.local.Character
import com.uk.androidrecruitmentapp.data.local.Episode
import com.uk.androidrecruitmentapp.data.local.Location
import com.uk.androidrecruitmentapp.data.remote.ApiService
import com.uk.androidrecruitmentapp.ui.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RickyMortyRepository(private val api: ApiService) {
    fun fetchEpisodes(activity: MainActivity): Observable<Episode> {
        return Observable.create { emitter ->
            api.getEpisodes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.body() != null) {
                            emitter.onNext(it.body()!!)
                        }
                    }, {
                        it.printStackTrace()
                        emitter.onError(it)
                    })
        }
    }

    fun fetchCharacters(activity: MainActivity): Observable<Character> {
        return Observable.create { emitter ->
            api.getCharacters()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.body() != null) {
                            emitter.onNext(it.body()!!)
                        }
                    }, {
                        it.printStackTrace()
                        activity.showConnectingError()
                    })
        }
    }

    fun fetchLocations(activity: MainActivity): Observable<Location> {
        return Observable.create { emitter ->
            api.getLocations()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.body() != null) {
                            emitter.onNext(it.body()!!)
                        }
                    }, {
                        it.printStackTrace()
                        activity.showConnectingError()
                    })
        }
    }
}