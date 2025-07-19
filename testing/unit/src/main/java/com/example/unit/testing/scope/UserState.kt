package com.example.unit.testing.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserState(
    private val userRepository: UserRepository,
    private val scope: CoroutineScope,
) {
    private val _users = MutableStateFlow(emptyList<String>())
    val users: StateFlow<List<String>> = _users.asStateFlow()

    fun registerUser(name: String) {
        scope.launch {
            userRepository.register(name)
            _users.update { userRepository.getAllUsers() }
        }
    }
}
