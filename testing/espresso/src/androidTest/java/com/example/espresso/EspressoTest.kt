package com.example.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule
    val activityScenario = ActivityScenarioRule(EspressoActivity::class.java)

    @Test
    fun checkFirstTest() {
        onView(withId(R.id.text1)).perform(ViewActions.click())
        onView(withId(R.id.text1)).check(matches(ViewMatchers.isClickable()))

        onView(withId(R.id.hello2)).check(matches(isDisplayed()))
//        onView(withId(R.id.hello2))
//            .check(matches(withText("updated")))
    }

}