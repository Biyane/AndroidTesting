package com.example.unit.coroutines

import com.example.unit.testing.scope.UserRepository
import com.example.unit.testing.scope.UserState
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserStateTest {

    @Test
    fun addUserTest()  = runTest {
        val userState = UserState(
            userRepository = UserRepository(ioDispatcher = StandardTestDispatcher(testScheduler)),
            scope = this
        )

        userState.registerUser("Nona")
        advanceUntilIdle()

        val users = userState.users.value

        assert(users.contains("Nona"))

    }

}