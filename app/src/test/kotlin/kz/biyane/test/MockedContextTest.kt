package kz.biyane.test

import android.content.Context
import com.example.myapplication2.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

private const val fakeString = "fakeString"
private const val fakeName = "fake name"

@RunWith(MockitoJUnitRunner::class)
class MockedContextTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        mockContext = mock<Context> {
            on { getString(R.string.fake_name) } doReturn fakeName
        }

        assertEquals(TestContextClass(mockContext).name, fakeName)
//        assertEquals(TestContextClass(mockContext).name, fakeString)

        mock<SomeClassWithMethod> {
            on { name } doReturn "name"
        }
    }
}

private class TestContextClass(val context: Context) {
    val name = context.getString(R.string.fake_name)
}

class SomeClassWithMethod {
    fun printSomething() {
        println("something")
    }

    val name = "name"
}