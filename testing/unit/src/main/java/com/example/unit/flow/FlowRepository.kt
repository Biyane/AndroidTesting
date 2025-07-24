package com.example.unit.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FlowRepository(
    private val dataSource: DataSource
) : IFlowRepository {
    override fun observeCount(): Flow<Int> {
        return flow {
            emit(1)
            delay(1000)
            emit(2)
        }
    }
    override fun scores(): Flow<Int> {
        return dataSource.counts()
    }
}

class FakeDataSource : DataSource {
    private val flow = MutableSharedFlow<Int>()
    suspend fun emit(value: Int) = flow.emit(value)
    override fun counts(): Flow<Int> = flow
}

interface DataSource {
    fun counts(): Flow<Int>
}