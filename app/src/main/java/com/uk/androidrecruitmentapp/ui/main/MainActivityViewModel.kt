package com.uk.androidrecruitmentapp.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uk.androidrecruitmentapp.RickyMortyRepository
import com.uk.androidrecruitmentapp.StateLiveData
import com.uk.androidrecruitmentapp.data.local.Character
import com.uk.androidrecruitmentapp.data.local.Episode
import com.uk.androidrecruitmentapp.data.local.Location
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins.onError
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

class MainActivityViewModel(private val rickyMortyRepository: RickyMortyRepository) : ViewModel() {
    val episodeLiveData : StateLiveData<Episode> = StateLiveData()
    val characterLiveData : StateLiveData<Character> = StateLiveData()
    val locationLiveData : StateLiveData<Location> = StateLiveData()

    @SuppressLint("CheckResult")
    fun getEpisodes(activity : MainActivity) {
        rickyMortyRepository
                .fetchEpisodes(activity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Episode> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: Episode) {
                       episodeLiveData.postSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        episodeLiveData.postError(e)
                    }

                    override fun onComplete() {}
                })

    }

    @SuppressLint("CheckResult")
    fun getCharacters(activity : MainActivity) {
        rickyMortyRepository
                .fetchCharacters(activity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Character> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: Character) {
                        characterLiveData.postSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        characterLiveData.postError(e)
                    }

                    override fun onComplete() {}
                })
    }

    @SuppressLint("CheckResult")
    fun getLocations(activity : MainActivity) {
        rickyMortyRepository
                .fetchLocations(activity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Location> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(t: Location) {
                        locationLiveData.postSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        locationLiveData.postError(e)
                    }

                    override fun onComplete() {}
                })
    }
}