package com.example.unit.testing

import kotlinx.coroutines.delay

suspend fun fetchData(): String {
    delay(1000L)
    return "Hello world"
}