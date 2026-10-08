package com.anthropic.models.beta.messages

import com.anthropic.core.STRING
import com.anthropic.core.X
import java.util.Optional
import kotlin.jvm.java
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the functions of the [StructuredContentBlock] class (delegator) that do more than
 * call the function of the same name in the wrapped [BetaContentBlock] (delegate).
 */
internal class StructuredContentBlockTest {
    companion object {
        private val TEXT_BLOCK = BetaTextBlock.builder().citations(null).text(STRING).build()
    }

    // New instances of the `mockDelegate` and `delegator` are required for each test case (each
    // test case runs in its own instance of the test class).
    private val mockDelegate: BetaContentBlock = mock(BetaContentBlock::class.java)
    private val delegator = StructuredContentBlock<X>(X::class.java, mockDelegate)

    @Test
    fun `delegation of text`() {
        // Input and output are different types, so this test is an exceptional case.
        // The delegator's `text()` delegates to the delegate's `text()` indirectly via the
        // delegator's `text` field initializer.
        val input = Optional.of(TEXT_BLOCK)
        `when`(mockDelegate.text()).thenReturn(input)
        val output = delegator.text()

        verify(mockDelegate, times(1)).text()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output.get().rawTextBlock).isEqualTo(TEXT_BLOCK)
    }

    @Test
    fun `delegation of asText`() {
        // Delegation function names do not match, so this test is an exceptional case.
        // The delegator's `asText()` delegates to the delegate's `text()` (without the "as")
        // indirectly via the delegator's `text` field initializer.
        val input = Optional.of(TEXT_BLOCK)
        `when`(mockDelegate.text()).thenReturn(input)
        val output = delegator.asText()

        verify(mockDelegate, times(1)).text()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output.rawTextBlock).isEqualTo(TEXT_BLOCK)
    }

    @Test
    fun `delegation of isText`() {
        // Delegation function names do not match, so this test is an exceptional case.
        // The delegator's `isText()` delegates to the delegate's `text()` (without the "is")
        // indirectly via the delegator's `text` field initializer.
        val input = Optional.of(TEXT_BLOCK)
        `when`(mockDelegate.text()).thenReturn(input)
        val output = delegator.isText()

        verify(mockDelegate, times(1)).text()
        verifyNoMoreInteractions(mockDelegate)

        assertThat(output).isTrue
    }

    @Test
    fun `delegation of validate`() {
        `when`(mockDelegate.text()).thenReturn(Optional.of(TEXT_BLOCK))

        delegator.validate()

        // Delegator's `validate()` does not call delegate's `validate()`. `text()` is called
        // indirectly via the `text` field initializer.
        verify(mockDelegate, times(1)).text()
        verifyNoMoreInteractions(mockDelegate)
    }

    @Test
    fun `delegation of isValid`() {
        // `isValid` calls `validate()`, so the test is similar to that for `validate()`.
        `when`(mockDelegate.text()).thenReturn(Optional.of(TEXT_BLOCK))

        delegator.isValid()

        verify(mockDelegate, times(1)).text()
        verifyNoMoreInteractions(mockDelegate)
    }
}
