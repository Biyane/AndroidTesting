package com.example.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.locks.ReentrantLock

private class Counter {
    private val lock = ReentrantLock()
    private var count = 0

    fun increment() {
        count++
        println("Count is now $count on ${Thread.currentThread().name}")
    }
}

fun main() {
    val counter = Counter()
    runBlocking {
        // Create multiple threads that increment the counter
        List(10) {
            launch(Dispatchers.Default) {
                repeat(100) { counter.increment() }
            }
        }.joinAll()
    }
}