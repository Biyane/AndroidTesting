package com.example.unit.flow

import app.cash.turbine.test
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class FirstFlowTest {

    @Test
    fun firstFlowTest() = runTest {
        val repository: IFlowRepository = FlowRepository(FakeDataSource())

        val first = repository.observeCount().first()

        assertEquals(first, 1)

    }


    @Test
    fun secondFlowTest() = runTest {
        val repository: IFlowRepository = FlowRepository(FakeDataSource())

        val allValues = repository.observeCount().toList()

        assertEquals(allValues, listOf(1,2))

    }

    @Test
    fun thirdFlowTest() = runTest {
        val dataSource = FakeDataSource()
        val repository: IFlowRepository = FlowRepository(dataSource)

        val values = mutableListOf<Int>()
        backgroundScope.launch(UnconfinedTestDispatcher()) {
            repository.scores().toList(values)
        }

        dataSource.emit(1)
        assertEquals(10, values.first())

        dataSource.emit(2)
        dataSource.emit(3)
        assertEquals(30, values[2])

    }

    @Test
    fun fourthFlowTest() = runTest {
        val dataSource = FakeDataSource()
        val repository: IFlowRepository = FlowRepository(dataSource)

        repository.scores().test {
            dataSource.emit(1)
            assertEquals(10, awaitItem())
        }
    }




}