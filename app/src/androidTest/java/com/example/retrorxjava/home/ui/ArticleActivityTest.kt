@file:Suppress("DEPRECATION")

package com.example.retrorxjava.home.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.rule.ActivityTestRule
import org.junit.Test
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrorxjava.R
import com.example.retrorxjava.web.ui.WebActivity
import org.junit.Before


class WebActivityTest1 {
    @Before
    fun setUp() {
        // enable java script for web page to perform web interactions


    }


    @Test
    fun activity_visible1() {
        val activityRule = ActivityScenario.launch(WebActivity::class.java)
        onView(withId(R.id.web_activity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        val activityRule = ActivityScenario.launch(WebActivity::class.java)
        onView(withId(R.id.next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.previous)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.INVISIBLE
                )
            )
        )
        onView(withId(R.id.titleName)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.backButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.webview)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )


    }

    @Test
    fun backbuttonclick() {
        val activityRule = ActivityScenario.launch(WebActivity::class.java)
        onView(withId(R.id.backButton)).perform(click())

        onView(withId(R.id.news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }

    @Test
    fun nextbuttonclick() {
        val activityRule = ActivityScenario.launch(WebActivity::class.java)
        onView(withId(R.id.next)).perform(click())

        onView(withId(R.id.webview)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )


    }

    @Test
    fun Previousbuttonclick() {
        val activityRule = ActivityScenario.launch(WebActivity::class.java)

        onView(withId(R.id.webview)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
        onView(withId(R.id.previous))
            .perform(click())


    }
}