package com.anthropic.models.beta.messages

import com.anthropic.core.DelegationReadTestCase
import com.anthropic.core.JSON_FIELD
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonField
import com.anthropic.core.OPTIONAL
import com.anthropic.core.X
import com.anthropic.core.checkAllDelegation
import com.anthropic.core.checkAllDelegatorReadFunctionsAreTested
import com.anthropic.core.checkOneDelegationRead
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
 * Unit tests for the [StructuredTextBlock] class (delegator) and its delegation of most functions
 * to a wrapped [BetaTextBlock] (delegate). The tests include confirmation of the following:
 * - All functions in the delegator correspond to a function in the delegate and _vice versa_.
 * - All functions in the delegator call their corresponding function in the delegate and only that
 *   function.
 * - A unit test exists for all functions.
 *
 * There are some exceptions to the above that are handled differently.
 */
internal class StructuredTextBlockTest {
    companion object {
        // The list order follows the declaration order in `BetaTextBlock` for easier maintenance.
        @JvmStatic
        private fun delegationTestCases() =
            listOf(
                DelegationReadTestCase("citations", OPTIONAL),
                // `text()` is a special case and has its own test function.
                DelegationReadTestCase("_type", JSON_VALUE),
                DelegationReadTestCase("_citations", JSON_FIELD),
                // `_text()` is a special case and has its own test function.
                DelegationReadTestCase("_additionalProperties", mapOf("key" to JSON_VALUE)),
            )
    }

    // New instances of the `mockDelegate` and `delegator` are required for each test case (each
    // test case runs in its own instance of the test class).
    private val mockDelegate: BetaTextBlock = mock(BetaTextBlock::class.java)
    private val delegator = StructuredTextBlock<X>(X::class.java, mockDelegate)

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
            exceptionalTestedFns = setOf("text", "_text", "validate", "isValid"),
            nonDelegatingFns = setOf("equals", "hashCode", "toString"),
        )
    }

    @ParameterizedTest
    @MethodSource("delegationTestCases")
    fun `delegation of functions in general`(testCase: DelegationReadTestCase) {
        checkOneDelegationRead(delegator, mockDelegate, testCase)
    }

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
