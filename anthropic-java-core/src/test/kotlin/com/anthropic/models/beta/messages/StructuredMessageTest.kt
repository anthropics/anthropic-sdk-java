package com.anthropic.models.beta.messages

import com.anthropic.core.JsonField
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.errors.AnthropicInvalidDataException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the functions of the [StructuredMessage] class (delegator) that do more than call
 * the function of the same name in the wrapped [BetaMessage] (delegate).
 */
internal class StructuredMessageTest {
    companion object {
        private val CONTENT =
            BetaContentBlock.ofText(BetaTextBlock.builder().citations(null).text(STRING).build())
    }

    // New instances of the `mockDelegate` and `delegator` are required for each test case (each
    // test case runs in its own instance of the test class).
    private val mockDelegate: BetaMessage = mock(BetaMessage::class.java)
    private val delegator = StructuredMessage<X>(X::class.java, mockDelegate)

    @Test
    fun `delegation of content`() {
        // Input and output are different types, so this test is an exceptional case.
        // `content()` (without an underscore) delegates to `_content()` (with an underscore)
        // indirectly via the `content` field initializer.
        val input = JsonField.of(listOf(CONTENT))
        `when`(mockDelegate._content()).thenReturn(input)
        val output = delegator.content() // Without an underscore.

        verify(mockDelegate, times(1))._content()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output[0].rawContentBlock).isEqualTo(CONTENT)
    }

    @Test
    fun `delegation of _content`() {
        // Input and output are different types, so this test is an exceptional case.
        // `_content()` delegates to `_content()` indirectly via the `content` field initializer.
        val input = JsonField.of(listOf(CONTENT))
        `when`(mockDelegate._content()).thenReturn(input)
        val output = delegator._content() // With an underscore.

        verify(mockDelegate, times(1))._content()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output.getRequired("_content")[0].rawContentBlock).isEqualTo(CONTENT)
    }

    @Test
    fun `delegation of validate`() {
        val input = JsonField.of(listOf(CONTENT))
        `when`(mockDelegate._content()).thenReturn(input)
        val output = delegator.validate()

        // `validate()` calls `content()` on the delegator which triggers the lazy initializer which
        // calls `_content()` on the delegate before `validate()` also calls `validate()` on the
        // delegate.
        verify(mockDelegate, times(1))._content()
        verify(mockDelegate, times(1)).validate()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output).isSameAs(delegator)
    }

    @Test
    fun `delegation of isValid when true`() {
        val input = JsonField.of(listOf(CONTENT))
        `when`(mockDelegate._content()).thenReturn(input)
        val output = delegator.isValid()

        // `isValid()` calls `validate()`, which has side effects explained in its test function.
        verify(mockDelegate, times(1))._content()
        verify(mockDelegate, times(1)).validate()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output).isTrue
    }

    @Test
    fun `delegation of isValid when false`() {
        // Try with a `false` value to make sure `isValid()` is not just hard-coded to `true`. Do
        // this by making `validate()` on the delegate throw an exception.
        val input = JsonField.of(listOf(CONTENT))
        `when`(mockDelegate._content()).thenReturn(input)
        `when`(mockDelegate.validate()).thenThrow(AnthropicInvalidDataException("test"))
        val output = delegator.isValid()

        // `isValid()` calls `validate()`, which has side effects explained in its test function.
        verify(mockDelegate, times(1))._content()
        verify(mockDelegate, times(1)).validate()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output).isFalse
    }
}
