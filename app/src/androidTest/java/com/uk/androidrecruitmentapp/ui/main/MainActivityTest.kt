package com.uk.androidrecruitmentapp.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.uk.androidrecruitmentapp.StateData
import com.uk.androidrecruitmentapp.data.local.Character
import com.uk.androidrecruitmentapp.data.local.Episode
import com.uk.androidrecruitmentapp.data.local.Location
import com.uk.androidrecruitmentapp.ui.fragments.EpisodesFragment
import com.uk.androidrecruitmentapp.ui.fragments.LocationsFragment
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    private val episodesTitle = "Episodes list"
    private val locationsTitle = "Locations list"
    private val errorInfo = "There was some error while connecting."

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testFirstScreenTest() {
        onView(withText("AndroidRecruitmentApp")).check(matches(isDisplayed()))
    }

    @Test
    fun handleEpisodeSuccessTest() {
        val episode = StateData<Episode>()
        episode.setStatus(StateData.DataStatus.SUCCESS)
        episode.setData(Episode())

        activityRule.scenario.onActivity { activity ->
            activity.handleEpisode(episode)
            onView(allOf(withText(episodesTitle), isDisplayed()))
        }
    }

    @Test
    fun handleEpisodeErrorTest() {
        val episode = StateData<Episode>()
        episode.setStatus(StateData.DataStatus.ERROR)

        activityRule.scenario.onActivity { activity ->
            activity.handleEpisode(episode)
            onView(allOf(withText(errorInfo), isDisplayed()))
        }
    }

    @Test
    fun handleCharacterErrorTest() {
        val character = StateData<Character>()
        character.setStatus(StateData.DataStatus.ERROR)

        activityRule.scenario.onActivity { activity ->
            activity.handleCharacter(character)
            onView(allOf(withText(errorInfo), isDisplayed()))
        }
    }

    @Test
    fun handleLocationSuccessTest() {
        val location = StateData<Location>()
        location.setStatus(StateData.DataStatus.SUCCESS)
        location.setData(Location())

        activityRule.scenario.onActivity { activity ->
            activity.handleLocation(location)
            onView(allOf(withText(locationsTitle), isDisplayed()))
        }
    }

    @Test
    fun handleLocationErrorTest() {
        val location = StateData<Location>()
        location.setStatus(StateData.DataStatus.ERROR)

        activityRule.scenario.onActivity { activity ->
            activity.handleLocation(location)
            onView(allOf(withText(errorInfo), isDisplayed()))
        }
    }

    @Test
    fun openEpisodesFragmentTest() {
        activityRule.scenario.onActivity { activity -> activity.openFragment(EpisodesFragment.newInstance(activity, Episode())) }
        onView(allOf(withText(episodesTitle), isDisplayed()))
    }

    @Test
    fun openLocationFragmentTest() {
        activityRule.scenario.onActivity { activity -> activity.openFragment(LocationsFragment.newInstance(activity, Location())) }
        onView(allOf(withText(locationsTitle), isDisplayed()))
    }

    @Test
    fun testShowConnectingErrorTest() {
        activityRule.scenario.onActivity { activity ->
            activity.showConnectingError()
            onView(allOf(withText(errorInfo), isDisplayed()))
        }
    }
}