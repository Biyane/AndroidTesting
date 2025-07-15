package kz.biyane.test

import android.content.Intent
import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication2.MainActivity
import com.example.myapplication2.test.ActivityForTest.ActivityForTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import kotlin.test.assertEquals
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.robolectric.annotation.Config
import java.util.regex.Pattern.matches


@Config(
    sdk = [Build.VERSION_CODES.TIRAMISU],
    shadows = []
)
@RunWith(AndroidJUnit4::class)
class FirstRobolectricTest {

    @Test
    fun firstTestWithRobolectric() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup()

            val activity = controller.get()

            val expected = Intent(activity, ActivityForTest::class.java)
            val actual = shadowOf(RuntimeEnvironment.getApplication()).nextStartedActivity

            assertEquals(expected, actual)
        }
    }

    @Test
    fun secondTestWithRobolectricAndEspresso() {
        // GIVEN
        val contactName = "Test User"
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // WHEN
        // Enter contact name
//        onView(withId(R.id.contact_name_text)).perform(typeText(contactName))
        // Destroy and recreate Activity
        scenario.recreate()

        // THEN
        // Check contact name was preserved.
//        onView(withId(R.id.contact_name_text)).check(matches(withText(contactName)))
    }

    @Test
    fun testEventFragment() {
//        val arguments = Bundle()
//        val factory = MyFragmentFactory()
//        val scenario = FragmentScenario.launchFragmentInContainer<MyFragment>(arguments, factory)
//        onView(withId(R.id.text)).check(matches(withText("Hello World!")))

        SomeTest::class.simpleName
    }
}

private class SomeTest