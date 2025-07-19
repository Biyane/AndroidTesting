package com.example.unit.coroutines

import com.example.unit.testing.fetchData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class FirstCoroutinesTesting  {

    @Test
    fun firstTestOfSuspend() {
        runTest {
            launch {

            }
            val result = fetchData()
            assertEquals("Hello world", result)
        }
    }

    @get:Rule
    val loggingRule: LoggingRule = LoggingRule()

    @Test
    fun standardTestFails() = runTest {
        val userRepo = UserRepository()

        launch { userRepo.register("Alice") }
        launch { userRepo.register("Bob") }

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ❌ Fails
    }

    @Test
    fun standardTest() = runTest(UnconfinedTestDispatcher()) {
        val userRepo = UserRepository()

        launch(StandardTestDispatcher()) {
            userRepo.register("Alice")
            delay(1)
            println("first Launch")
        }
        launch {
            userRepo.register("Bob")
            println("second Launch")
        }
//        advanceUntilIdle()
//        advanceTimeBy(500)
//        runCurrent()

        println("last line in test")
    }

    @Test
    fun yieldingTest() = runTest(UnconfinedTestDispatcher()) {
        val userRepo = UserRepository()

        launch {
            userRepo.register("Alice")
            delay(10L)
            userRepo.register("Bob")
        }

        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ❌ Fails
    }
}

private class UserRepository {

    private val mutex = Mutex()

    private val users = mutableListOf<String>()
    suspend fun register(name: String) {
        mutex.withLock {
            users.add(name)
        }
    }

    suspend fun getAllUsers(): List<String> {
        mutex.withLock {
            return users
        }
    }
}

class LoggingRule : TestWatcher() {
    override fun starting(description: Description) {
        println("Starting test: ${description.displayName}")
    }

    override fun finished(description: Description) {
        println("Finished test: ${description.displayName}")
    }
}