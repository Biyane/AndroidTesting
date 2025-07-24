package com.example.unit.flow

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class SecondHotFlowTest {

    @Test
    fun hotFLowTest() = runTest {
        val dataSource = FakeDataSource()
        val repository = FlowRepository(dataSource)

        val viewModel = FlowViewModel(repository, UnconfinedTestDispatcher())
        assertEquals( 0, viewModel.score.value)

        viewModel.initialize()

        dataSource.emit(1)

        assertEquals(1, viewModel.score.value)

        dataSource.emit(2)
        dataSource.emit(3)
        assertEquals(3, viewModel.score.value) // Assert on the latest value

    }

    @Test
    fun stateInTest() = runTest  {
        val dataSource = FakeDataSource()
        val repository = FlowRepository(dataSource)

        val viewModel = FlowViewModel(repository)

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.stateInScore.collect {

            }
        }
        assertEquals(0, viewModel.stateInScore.value)

        dataSource.emit(1)
        assertEquals(1, viewModel.stateInScore.value)

        dataSource.emit(2)
        dataSource.emit(3)

        assertEquals(3, viewModel.stateInScore.value)

    }
}