package com.anthropic.models.beta.messages

import com.anthropic.core.DelegationReadTestCase
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.OPTIONAL
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.core.checkAllDelegation
import com.anthropic.core.checkAllDelegatorReadFunctionsAreTested
import com.anthropic.core.checkOneDelegationRead
import java.util.Optional
import kotlin.jvm.java
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
 * Unit tests for the [StructuredContentBlock] class (delegator) and its delegation of most
 * functions to a wrapped [BetaContentBlock] (delegate). The tests include confirmation of the
 * following:
 * - All functions in the delegator correspond to a function in the delegate and _vice versa_.
 * - All functions in the delegator call their corresponding function in the delegate and only that
 *   function.
 * - A unit test exists for all functions.
 *
 * There are some exceptions to the above that are handled differently.
 */
internal class StructuredContentBlockTest {
    companion object {
        private val TEXT_BLOCK = BetaTextBlock.builder().citations(null).text(STRING).build()
        private val THINKING =
            BetaThinkingBlock.builder().signature(STRING).thinking(STRING).build()
        private val REDACTED_THINKING = BetaRedactedThinkingBlock.builder().data(STRING).build()
        private val TOOL_USE =
            BetaToolUseBlock.builder().id(STRING).input(JSON_VALUE).name(STRING).build()
        private val SERVER_TOOL_USE =
            BetaServerToolUseBlock.builder()
                .id(STRING)
                .input(JSON_VALUE)
                .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                .build()
        private val WEB_SEARCH_TOOL_RESULT =
            BetaWebSearchToolResultBlock.builder()
                .content(
                    BetaWebSearchToolResultBlockContent.ofError(
                        BetaWebSearchToolResultError.builder()
                            .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                )
                .toolUseId(STRING)
                .build()
        private val WEB_FETCH_TOOL_RESULT =
            BetaWebFetchToolResultBlock.builder()
                .content(
                    BetaWebFetchToolResultBlock.Content.ofBetaWebFetchToolResultErrorBlock(
                        BetaWebFetchToolResultErrorBlock.builder()
                            .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                )
                .toolUseId(STRING)
                .build()
        private val CODE_EXECUTION_TOOL_RESULT =
            BetaCodeExecutionToolResultBlock.builder()
                .content(
                    BetaCodeExecutionToolResultError.builder()
                        .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId(STRING)
                .build()
        private val BASH_CODE_EXECUTION_TOOL_RESULT =
            BetaBashCodeExecutionToolResultBlock.builder()
                .content(
                    BetaBashCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId(STRING)
                .build()
        private val TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT =
            BetaTextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .errorMessage(null)
                        .build()
                )
                .toolUseId(STRING)
                .build()
        private val MCP_TOOL_USE =
            BetaMcpToolUseBlock.builder()
                .id(STRING)
                .name(STRING)
                .serverName(STRING)
                .input(JSON_VALUE)
                .build()
        private val MCP_TOOL_RESULT =
            BetaMcpToolResultBlock.builder().content(STRING).isError(true).toolUseId(STRING).build()
        private val CONTAINER_UPLOAD = BetaContainerUploadBlock.builder().fileId(STRING).build()

        // The list order follows the declaration order in `BetaContentBlock` for easier
        // maintenance.
        @JvmStatic
        private fun delegationTestCases() =
            listOf(
                // `text()` is a special case and has its own test function.
                DelegationReadTestCase("thinking", OPTIONAL),
                DelegationReadTestCase("redactedThinking", OPTIONAL),
                DelegationReadTestCase("toolUse", OPTIONAL),
                DelegationReadTestCase("serverToolUse", OPTIONAL),
                DelegationReadTestCase("webSearchToolResult", OPTIONAL),
                DelegationReadTestCase("webFetchToolResult", OPTIONAL),
                DelegationReadTestCase("codeExecutionToolResult", OPTIONAL),
                DelegationReadTestCase("bashCodeExecutionToolResult", OPTIONAL),
                DelegationReadTestCase("textEditorCodeExecutionToolResult", OPTIONAL),
                DelegationReadTestCase("mcpToolUse", OPTIONAL),
                DelegationReadTestCase("mcpToolResult", OPTIONAL),
                DelegationReadTestCase("containerUpload", OPTIONAL),
                // `isText()` is a special case and has its own test function.
                // For the Boolean functions, call each in turn with both `true` and `false` to
                // ensure that a return value is not hard-coded.
                DelegationReadTestCase("isThinking", true),
                DelegationReadTestCase("isThinking", false),
                DelegationReadTestCase("isRedactedThinking", true),
                DelegationReadTestCase("isRedactedThinking", false),
                DelegationReadTestCase("isToolUse", true),
                DelegationReadTestCase("isToolUse", false),
                DelegationReadTestCase("isServerToolUse", true),
                DelegationReadTestCase("isServerToolUse", false),
                DelegationReadTestCase("isWebSearchToolResult", true),
                DelegationReadTestCase("isWebSearchToolResult", false),
                DelegationReadTestCase("isWebFetchToolResult", true),
                DelegationReadTestCase("isWebFetchToolResult", false),
                DelegationReadTestCase("isWebFetchToolResult", true),
                DelegationReadTestCase("isWebFetchToolResult", false),
                DelegationReadTestCase("isCodeExecutionToolResult", true),
                DelegationReadTestCase("isCodeExecutionToolResult", false),
                DelegationReadTestCase("isBashCodeExecutionToolResult", true),
                DelegationReadTestCase("isBashCodeExecutionToolResult", false),
                DelegationReadTestCase("isTextEditorCodeExecutionToolResult", true),
                DelegationReadTestCase("isTextEditorCodeExecutionToolResult", false),
                DelegationReadTestCase("isMcpToolUse", true),
                DelegationReadTestCase("isMcpToolUse", false),
                DelegationReadTestCase("isMcpToolResult", true),
                DelegationReadTestCase("isMcpToolResult", false),
                DelegationReadTestCase("isContainerUpload", true),
                DelegationReadTestCase("isContainerUpload", false),
                // `asText()` is a special case and has its own test function.
                DelegationReadTestCase("asThinking", THINKING),
                DelegationReadTestCase("asRedactedThinking", REDACTED_THINKING),
                DelegationReadTestCase("asToolUse", TOOL_USE),
                DelegationReadTestCase("asServerToolUse", SERVER_TOOL_USE),
                DelegationReadTestCase("asWebSearchToolResult", WEB_SEARCH_TOOL_RESULT),
                DelegationReadTestCase("asWebFetchToolResult", WEB_FETCH_TOOL_RESULT),
                DelegationReadTestCase("asCodeExecutionToolResult", CODE_EXECUTION_TOOL_RESULT),
                DelegationReadTestCase(
                    "asBashCodeExecutionToolResult",
                    BASH_CODE_EXECUTION_TOOL_RESULT,
                ),
                DelegationReadTestCase(
                    "asTextEditorCodeExecutionToolResult",
                    TEXT_EDITOR_CODE_EXECUTION_TOOL_RESULT,
                ),
                DelegationReadTestCase("asMcpToolUse", MCP_TOOL_USE),
                DelegationReadTestCase("asMcpToolResult", MCP_TOOL_RESULT),
                DelegationReadTestCase("asContainerUpload", CONTAINER_UPLOAD),
                DelegationReadTestCase("_json", OPTIONAL),
            )
    }

    // New instances of the `mockDelegate` and `delegator` are required for each test case (each
    // test case runs in its own instance of the test class).
    private val mockDelegate: BetaContentBlock = mock(BetaContentBlock::class.java)
    private val delegator = StructuredContentBlock<X>(X::class.java, mockDelegate)

    @Test
    fun allDelegateFunctionsExistInDelegator() {
        // `toParam()` is deliberately not implemented. `accept()` has a different signature.
        checkAllDelegation(mockDelegate::class, delegator::class, "toParam", "accept")
    }

    @Test
    fun allDelegatorFunctionsExistInDelegate() {
        // `accept()` has a different signature.
        checkAllDelegation(delegator::class, mockDelegate::class, "accept")
    }

    @Test
    fun allDelegatorFunctionsAreTested() {
        // There are exceptional test cases for some functions. Most other functions are part of the
        // list of those using the parameterized test. A few delegator functions do not delegate, so
        // no test function is necessary.
        checkAllDelegatorReadFunctionsAreTested(
            delegator::class,
            delegationTestCases(),
            exceptionalTestedFns =
                setOf("text", "asText", "isText", "toParam", "accept", "validate", "isValid"),
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
