@file:OptIn(ExperimentalAtomicApi::class)

package com.example.unit.coroutines

import com.example.unit.testing.scope.BetterRepository
import com.example.unit.testing.scope.UserRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.concurrent.atomics.ExperimentalAtomicApi

class RepositoryTest {
    @Test
    fun repoInitWorksAndDataIsHelloWorld() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = UserRepository(dispatcher)

        repository.initialize()
        advanceUntilIdle() // Runs the new coroutine
        assertEquals(true, repository.initialized.get())

        val data = repository.fetchData() // No thread switch, delay is skipped
        assertEquals("Hello world", data)
    }

    @Test
    fun repoInitWorks() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BetterRepository(dispatcher)

        launch(StandardTestDispatcher()) {
            repository.initialize().await() // Suspends until the new coroutine is done
            assertEquals(true, repository.initialized.get())
        }
        advanceUntilIdle() // Runs the new coroutine

        val data = repository.fetchData() // No thread switch, delay is skipped
        assertEquals("Hello world", data)
    }

    @Test
    fun testingMainDispatcher() {
        val testDispatcher = StandardTestDispatcher()

    }

}