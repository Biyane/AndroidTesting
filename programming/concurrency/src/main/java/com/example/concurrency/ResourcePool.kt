package com.example.concurrency

import java.util.concurrent.locks.ReentrantLock

class ResourcePool<T>(private val resources: List<T>) {
    private val lock = ReentrantLock()
    private val available = resources.toMutableList()

    fun acquire(timeoutMillis: Long): T? {
        if (lock.tryLock(timeoutMillis, java.util.concurrent.TimeUnit.MILLISECONDS)) {
            try {
                if (available.isNotEmpty()) {
                    return available.removeAt(0)
                }
            } finally {
                lock.unlock()
            }
        }
        return null
    }

    fun release(resource: T) {
        lock.lock()
        try {
            available.add(resource)
        } finally {
            lock.unlock()
        }
    }
}

fun main() {
    val pool = ResourcePool(listOf("A", "B", "C"))

    val thread1 = Thread {
        val resource = pool.acquire(1000)
        if (resource != null) {
            println("Thread 1 acquired $resource")
            Thread.sleep(500)
            pool.release(resource)
        } else {
            println("Thread 1 failed to acquire resource")
        }
    }

    val thread2 = Thread {
        val resource = pool.acquire(100)
        if (resource != null) {
            println("Thread 2 acquired $resource")
            Thread.sleep(200)
            pool.release(resource)
        } else {
            println("Thread 2 failed to acquire resource")
        }
    }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
}