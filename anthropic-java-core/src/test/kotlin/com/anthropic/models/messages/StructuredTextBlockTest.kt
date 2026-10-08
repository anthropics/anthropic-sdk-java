package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.X
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the functions of the GA [StructuredTextBlock] class (delegator) that do more than
 * call the function of the same name in the wrapped [TextBlock] (delegate).
 */
internal class StructuredTextBlockTest {
    // New instances of the `mockDelegate` and `delegator` are required for each test case (each
    // test case runs in its own instance of the test class).
    private val mockDelegate: TextBlock = mock(TextBlock::class.java)
    private val delegator = StructuredTextBlock<X>(X::class.java, mockDelegate)

    @Test
    fun `delegation of text`() {
        // Input and output are different types, so this test is an exceptional case.
        // `text()` (without an underscore) delegates to `_text()` (with an underscore)
        // indirectly via the `text` field initializer.
        val input = JsonField.of("{\"s\" : \"hello\"}")
        `when`(mockDelegate._text()).thenReturn(input)
        val output = delegator.text() // Without an underscore.

        verify(mockDelegate, times(1))._text()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output).isEqualTo(X("hello"))
    }

    @Test
    fun `delegation of _text`() {
        // Input and output are different types, so this test is an exceptional case.
        // `_text()` delegates to `_text()` indirectly via the `text` field initializer.
        val input = JsonField.of("{\"s\" : \"hello\"}")
        `when`(mockDelegate._text()).thenReturn(input)
        val output = delegator._text() // With an underscore.

        verify(mockDelegate, times(1))._text()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output).isEqualTo(JsonField.of(X("hello")))
    }
}
