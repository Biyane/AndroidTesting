package com.example.myapplication2.concurrency

import kotlin.concurrent.thread

fun main() {
    val lock1 = Any()
    val lock2 = Any()

    lock1.equals()
    lock1.javaClass

    val thread1 = thread {

        lock1::class.java
        synchronized(lock1) {
            println("Thread 1: Holding lock 1...")
            Thread.sleep(100)
            println("Thread 1: Waiting for lock 2...")
            synchronized(lock2) {
                println("Thread 1: Acquired lock 2!")
            }
        }
    }

    val thread2 = thread {
        synchronized(lock2) {
            println("Thread 2: Holding lock 2...")
            Thread.sleep(100)
            println("Thread 2: Waiting for lock 1...")
            synchronized(lock1) {
                println("Thread 2: Acquired lock 1!")
            }
        }
    }

    thread1.join()
    thread2.join()
}