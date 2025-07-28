package com.example.unit.robolectric

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.unit.robolectirc.RobolectricActivity
import com.example.unit.robolectirc.RobolectricActivity2
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(AndroidJUnit4::class)
class RobolectricTest {

    private lateinit var activity: RobolectricActivity

    @Before
    fun setupActivity() {
        activity =
            Robolectric.buildActivity(RobolectricActivity::class.java)
                .setup()
//                .create()
//                .start()
//                .resume()
                .get()

    }

    @Test
    fun notNullActivity() {
        assertNotNull(activity)
    }

    @Test
    fun openNextActivity() {
        activity.nextActivity()

        val expected = shadowOf(RuntimeEnvironment.getApplication()).nextStartedActivity
        val actual = Intent(activity, RobolectricActivity2::class.java)

        assertEquals(expected, actual)
    }
}
