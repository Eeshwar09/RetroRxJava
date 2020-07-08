@file:Suppress("DEPRECATION")

package com.example.retrorxjava.home.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.rule.ActivityTestRule
import org.junit.Test
import android.content.Intent
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.retrorxjava.R
import com.example.retrorxjava.web.ui.WebActivity


class WebActivityTest1 {
    var activityrule = ActivityTestRule(WebActivity::class.java)
    var activity: WebActivity? = null



    @Test
    fun onBackPressed() {
        onView(isRoot()).perform(ViewActions.pressBack())


    }

    @Test
    fun backbutton() {
        Intents.init()

        activityrule.launchActivity(Intent())
        onView(withId(R.id.backButton)).perform(click())

        intended(hasComponent(HomeActivity::class.java.name))

        Intents.release()
    }
}