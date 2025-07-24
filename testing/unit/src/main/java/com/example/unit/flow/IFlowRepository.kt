package com.example.unit.flow

import kotlinx.coroutines.flow.Flow

interface IFlowRepository {

    fun observeCount(): Flow<Int>
    fun scores(): Flow<Int>
}