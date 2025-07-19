package com.example.unit.testing.scope

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean


// Example class demonstrating dispatcher use cases
class UserRepository(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val scope = CoroutineScope(ioDispatcher)

    val initialized = AtomicBoolean(false)

    // A function that starts a new coroutine on the IO dispatcher
    fun initialize() {
        scope.launch {
            initialized.set(true)
        }
    }

    // A suspending function that switches to the IO dispatcher
    suspend fun fetchData(): String = withContext(ioDispatcher) {
        require(initialized.get()) { "Repository should be initialized first" }
        delay(500L)
        "Hello world"
    }

    private val users = mutableListOf<String>()
    private val mutex = Mutex()
    suspend fun register(name: String) {
        // ...
        mutex.withLock {
            users.add(name)
        }
    }

    suspend fun getAllUsers() : List<String> {
        return mutex.withLock { users }
    }
}

class BetterRepository(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val scope = CoroutineScope(ioDispatcher)

    val initialized = AtomicBoolean(false)

    fun initialize() = scope.async {
        // ...
        initialized.set(true)
    }



    // A suspending function that switches to the IO dispatcher
    suspend fun fetchData(): String = withContext(ioDispatcher) {
        require(initialized.get()) { "Repository should be initialized first" }
        delay(500L)
        "Hello world"
    }
}