package com.uk.androidrecruitmentapp.ui.main

import android.os.Bundle
import android.os.Handler
import android.text.format.DateUtils
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.uk.androidrecruitmentapp.ARApplication
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.StateData
import com.uk.androidrecruitmentapp.data.local.Character
import com.uk.androidrecruitmentapp.data.local.Episode
import com.uk.androidrecruitmentapp.data.local.Location
import com.uk.androidrecruitmentapp.data.remote.ApiService
import com.uk.androidrecruitmentapp.factory.RepositoryFactory
import com.uk.androidrecruitmentapp.factory.ViewModelFactory
import com.uk.androidrecruitmentapp.ui.fragments.CharactersFragment
import com.uk.androidrecruitmentapp.ui.fragments.EpisodesFragment
import com.uk.androidrecruitmentapp.ui.fragments.LocationsFragment
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var toolbar: ActionBar

    @Inject
    lateinit var service: ApiService
    private lateinit var bottomNavigation: BottomNavigationView

    private lateinit var onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ARApplication.apiComponent.inject(this)

        toolbar = supportActionBar!!

        onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.page_1 -> {
                    if (bottomNavigation.selectedItemId != item.itemId) {
                        progress_bar.visibility = View.VISIBLE
                        toolbar.title = getString(R.string.episodes)
                        mainActivityViewModel.getEpisodes(this)
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.page_2 -> {
                    if (bottomNavigation.selectedItemId != item.itemId) {
                        progress_bar.visibility = View.VISIBLE
                        toolbar.title = getString(R.string.characters)
                        mainActivityViewModel.getCharacters(this)
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.page_3 -> {
                    if (bottomNavigation.selectedItemId != item.itemId) {
                        progress_bar.visibility = View.VISIBLE
                        toolbar.title = getString(R.string.locations)
                        mainActivityViewModel.getLocations(this)
                    }
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        mainActivityViewModel = ViewModelProviders.of(this, ViewModelFactory(RepositoryFactory.createRMRepository())).get(MainActivityViewModel::class.java)
        mainActivityViewModel.episodeLiveData.observe(this, this::handleEpisode)
        mainActivityViewModel.characterLiveData.observe(this, this::handleCharacter)
        mainActivityViewModel.locationLiveData.observe(this, this::handleLocation)

        mainActivityViewModel.getEpisodes(this)
    }

    internal fun handleEpisode(@NonNull episode: StateData<Episode>?) {
        when (episode?.getStatus()) {
            StateData.DataStatus.SUCCESS -> {
                Handler().postDelayed({
                    progress_bar.visibility = View.GONE
                }, DateUtils.SECOND_IN_MILLIS)

                openFragment(EpisodesFragment.newInstance(this, episode.getData()))
            }
            StateData.DataStatus.ERROR -> {
                showConnectingError()
            }
            else -> {}
        }
    }

    internal fun handleCharacter(@NonNull character: StateData<Character>?) {
        when (character?.getStatus()) {
            StateData.DataStatus.SUCCESS -> {
                Handler().postDelayed({
                    progress_bar.visibility = View.GONE
                }, DateUtils.SECOND_IN_MILLIS)

                openFragment(CharactersFragment.newInstance(this, character.getData()))
            }
            StateData.DataStatus.ERROR -> {
                showConnectingError()
            }
            else -> {}
        }
    }

    internal fun handleLocation(@NonNull location: StateData<Location>?) {
        when (location?.getStatus()) {
            StateData.DataStatus.SUCCESS -> {
                Handler().postDelayed({
                    progress_bar.visibility = View.GONE
                }, DateUtils.SECOND_IN_MILLIS)

                openFragment(LocationsFragment.newInstance(this, location.getData()))
            }
            StateData.DataStatus.ERROR -> {
                showConnectingError()
            }
            else -> {}
        }
    }

    internal fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    internal fun showConnectingError() {
        Snackbar.make(findViewById(R.id.container), getString(R.string.error), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.close)) {}
                .show()
    }
}