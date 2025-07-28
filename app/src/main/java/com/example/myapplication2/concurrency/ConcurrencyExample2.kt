package com.example.myapplication2.concurrency

import kotlin.concurrent.thread

class BankAccount(val id: Int, var balance: Int) {
    fun transfer(to: BankAccount, amount: Int) {
        synchronized(this) {
            println("${Thread.currentThread().name} 1 acquired lock on account $id ")
            synchronized(to) {
                println("${Thread.currentThread().name} 2 acquired lock on account ${to.id}")
                if (balance >= amount) {
                    balance -= amount
                    to.balance += amount
                    println("Transferred $amount from $id to ${to.id}")
                }
            }
        }
    }
}

fun main() {
    val account1 = BankAccount(1, 1000)
    val account2 = BankAccount(2, 1000)

    val thread1 = thread(name = "Thread-1") {
        account1.transfer(account2, 100)
    }

    val thread2 = thread(name = "Thread-2") {
        account2.transfer(account1, 200)
    }

    thread1.join()
    thread2.join()
}