package kz.biyane.test

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication2.MainActivity
import com.example.myapplication2.test.ActivityForTest.ActivityForTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import org.robolectric.shadow.api.Shadow
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
@Config(
    shadows = [SomeTestShadow::class],
)
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
    }

    @Test
    fun testingShadows() {
        // Let Robolectric create or manage the instance if possible,
        // or ensure it's created in an environment where Robolectric can intercept it.

        // For classes you instantiate directly, you often don't call Shadows.shadowOf()
        // on them directly unless they are framework classes.
        // Instead, you interact with the real object, and your shadow's
        // @Implementation methods will be invoked.

        val someTest = SomeTest()

        val shadowSomeTest = Shadow.extract(someTest) as SomeTestShadow

        // Now you can call methods on your shadow:
        shadowSomeTest.setMockName("Robolectric Test")
        assertEquals("Robolectric Test", someTest.name) // Verify the shadow's @Implementation was called

    }
}

private class SomeTest {
    var name = "Kemel"
}

@Implements(SomeTest::class)
private class SomeTestShadow {
    @RealObject
    lateinit var realObject: SomeTest

    private var mockName = "DefaultMockName"

    @Implementation
    fun getName(): String = mockName

    fun mockName() {

    }

    fun setMockName(name: String) {
        mockName = name
    }
}