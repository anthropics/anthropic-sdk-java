package com.anthropic.models.beta.messages

import com.anthropic.core.DelegationReadTestCase
import com.anthropic.core.JSON_FIELD
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonField
import com.anthropic.core.LONG
import com.anthropic.core.OPTIONAL
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.core.checkAllDelegation
import com.anthropic.core.checkAllDelegatorReadFunctionsAreTested
import com.anthropic.core.checkOneDelegationRead
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the [StructuredMessage] class (delegator) and its delegation of most functions to
 * a wrapped [BetaMessage] (delegate). The tests include confirmation of the following:
 * - All functions in the delegator correspond to a function in the delegate and _vice versa_.
 * - All functions in the delegator call their corresponding function in the delegate and only that
 *   function.
 * - A unit test exists for all functions.
 *
 * There are some exceptions to the above that are handled differently.
 */
internal class StructuredMessageTest {
    companion object {
        private val MODEL = Model.CLAUDE_4_SONNET_20250514
        private val USAGE =
            BetaUsage.builder()
                .cacheCreation(null)
                .cacheCreationInputTokens(null)
                .cacheReadInputTokens(null)
                .inputTokens(LONG)
                .outputTokens(LONG)
                .serverToolUse(null)
                .serviceTier(null)
                .build()
        private val CONTENT =
            BetaContentBlock.ofText(BetaTextBlock.builder().citations(null).text(STRING).build())

        // The list order follows the declaration order in `BetaMessage` for easier maintenance.
        @JvmStatic
        private fun delegationTestCases() =
            listOf(
                DelegationReadTestCase("id", STRING),
                DelegationReadTestCase("container", OPTIONAL),
                // `content()` is a special case and has its own test function.
                DelegationReadTestCase("contextManagement", OPTIONAL),
                DelegationReadTestCase("model", MODEL),
                DelegationReadTestCase("_role", JSON_VALUE),
                DelegationReadTestCase("stopReason", OPTIONAL),
                DelegationReadTestCase("stopSequence", OPTIONAL),
                DelegationReadTestCase("_type", JSON_VALUE),
                DelegationReadTestCase("usage", USAGE),
                DelegationReadTestCase("_id", JSON_FIELD),
                DelegationReadTestCase("_container", JSON_FIELD),
                // `_content()` is a special case and has its own test function.
                DelegationReadTestCase("_contextManagement", JSON_FIELD),
                DelegationReadTestCase("_model", JSON_FIELD),
                DelegationReadTestCase("_stopReason", JSON_FIELD),
                DelegationReadTestCase("_stopSequence", JSON_FIELD),
                DelegationReadTestCase("_usage", JSON_FIELD),
                DelegationReadTestCase("_additionalProperties", mapOf("key" to JSON_VALUE)),
            )
    }

    // New instances of the `mockDelegate` and `delegator` are required for each test case (each
    // test case runs in its own instance of the test class).
    private val mockDelegate: BetaMessage = mock(BetaMessage::class.java)
    private val delegator = StructuredMessage<X>(X::class.java, mockDelegate)

    @Test
    fun allDelegateFunctionsExistInDelegator() {
        checkAllDelegation(mockDelegate::class, delegator::class, "toBuilder", "toParam")
    }

    @Test
    fun allDelegatorFunctionsExistInDelegate() {
        checkAllDelegation(delegator::class, mockDelegate::class)
    }

    @Test
    fun allDelegatorFunctionsAreTested() {
        // There are exceptional test cases for some functions. Most other functions are part of the
        // list of those using the parameterized test. A few delegator functions do not delegate, so
        // no test function is necessary.
        checkAllDelegatorReadFunctionsAreTested(
            delegator::class,
            delegationTestCases(),
            exceptionalTestedFns = setOf("content", "_content", "validate", "isValid"),
            nonDelegatingFns = setOf("equals", "hashCode", "toString"),
        )
    }

    @ParameterizedTest
    @MethodSource("delegationTestCases")
    fun `delegation of functions in general`(testCase: DelegationReadTestCase) {
        checkOneDelegationRead(delegator, mockDelegate, testCase)
    }

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
