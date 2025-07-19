package com.example.unit.viewmodel

import com.example.unit.testing.ViewModelTest1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.test.assertEquals

class ViewModelTest1Test {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val testScope = TestScope() // C
    @Test
    fun testDispatcherMain() = testScope.runTest {

        val viewModel = ViewModelTest1()
        viewModel.loadMessage()

        assertEquals("Greetings!", viewModel.message.value)

    }
    val testScope1 = TestScope() // Creates a StandardTestDispatcher

    @Test
    fun someTest() = testScope1.runTest {
        // ...
        assertEquals(2, 1 + 1)
    }

}


class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}

