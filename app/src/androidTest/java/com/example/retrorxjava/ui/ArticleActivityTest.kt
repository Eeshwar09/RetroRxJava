@file:Suppress("DEPRECATION")

package com.example.retrorxjava.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Test
import android.content.Intent
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrorxjava.R


class ArticleActivityTest1 {
    var activityrule = ActivityTestRule(ArticleActivity::class.java)
    var activity: ArticleActivity? = null
    @Before
    fun setUp() {
        activity = activityrule.activity
    }

    @After
    fun tearDown() {
        activity = null
    }

    @Test
    fun onCreate() {
        val webFormIntent = Intent()
        activityrule.launchActivity(webFormIntent)

    }

    @Test
    fun onBackPressed() {
        onView(isRoot()).perform(ViewActions.pressBack())


    }

    @Test
    fun backbutton() {
        Intents.init()

        activityrule.launchActivity(Intent())
        onView(withId(R.id.backButton)).perform(click())

        intended(hasComponent(BookActivity::class.java.name))

        Intents.release()
    }
}