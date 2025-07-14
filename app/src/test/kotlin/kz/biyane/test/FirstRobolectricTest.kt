package kz.biyane.test

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication2.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstRobolectricTest {

    @Test
    fun firstTestWithRobolectric() {
        //given
        val name = "firstName"
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        //when
        onView()
        scenario.recreate()

        //then

    }
}