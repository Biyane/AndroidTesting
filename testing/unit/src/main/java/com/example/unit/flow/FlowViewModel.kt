package com.example.unit.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FlowViewModel(
    private val myRepository: FlowRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    val stateInScore: StateFlow<Int> = myRepository.scores().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        0
    )

    fun initialize() {
        viewModelScope.launch(dispatcher) {
            myRepository.scores().collect { score ->
                _score.value = score
            }
        }
    }
}