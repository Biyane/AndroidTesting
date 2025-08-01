package com.example.instrumented

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest // Optional runner annotation
class ChangeTextBehaviorTest {
    val stringToBeTyped = "Espresso"
    // ActivityTestRule accesses context through the runner
    @get:Rule
    val activityRule = ActivityScenarioRule(InstrumentedMainActivity::class.java)

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//            .perform(typeText(stringToBeTyped), closeSoftKeyboard())
//        onView(withId(R.id.changeTextBt)).perform(click())
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//            .check(matches(withText(stringToBeTyped)))
    }
}