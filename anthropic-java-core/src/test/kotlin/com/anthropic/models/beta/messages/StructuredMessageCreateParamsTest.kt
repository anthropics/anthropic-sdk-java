package com.anthropic.models.beta.messages

import com.anthropic.core.CLASS
import com.anthropic.core.DOUBLE
import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JSON_FIELD
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.LIST
import com.anthropic.core.LONG
import com.anthropic.core.MAP
import com.anthropic.core.OPTIONAL
import com.anthropic.core.SET
import com.anthropic.core.STRING
import com.anthropic.core.X
import com.anthropic.core.betaOutputFormatFromClass
import com.anthropic.core.checkAllDelegation
import com.anthropic.core.checkAllDelegatorWriteFunctionsAreTested
import com.anthropic.core.checkOneDelegationWrite
import com.anthropic.core.findDelegationMethod
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toolFromClass
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.messages.Model
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the [StructuredMessageCreateParams] class (delegator) and its delegation of most
 * functions to a wrapped [MessageCreateParams] (delegate). It is the `Builder` class of each main
 * class that is involved in the delegation. The tests include confirmation of the following:
 * - All functions in the delegator correspond to a function in the delegate and _vice versa_.
 * - All functions in the delegator call their corresponding function in the delegate and only that
 *   function.
 * - A unit test exists for all functions.
 *
 * There are some exceptions to the above that are handled differently.
 */
internal class StructuredMessageCreateParamsTest {
    companion object {
        private val MODEL = Model.CLAUDE_4_SONNET_20250514
        private val ANTHROPIC_BETA = AnthropicBeta.TOKEN_COUNTING_2024_11_01

        private val MESSAGE =
            BetaMessage.builder()
                .id(STRING)
                .container(null)
                .content(listOf())
                .model(MODEL)
                .stopReason(BetaStopReason.STOP_SEQUENCE)
                .stopSequence(null)
                .usage(
                    BetaUsage.builder()
                        .cacheCreation(null)
                        .cacheCreationInputTokens(null)
                        .cacheReadInputTokens(null)
                        .inferenceGeo("inference_geo")
                        .inputTokens(LONG)
                        .iterations(null)
                        .outputTokens(LONG)
                        .serverToolUse(null)
                        .serviceTier(null)
                        .speed(null)
                        .build()
                )
                .contextManagement(null)
                .build()
        private val MESSAGE_PARAM =
            BetaMessageParam.builder().content(STRING).role(BetaMessageParam.Role.USER).build()
        private val MESSAGE_PARAM_CONTENT = BetaMessageParam.Content.ofString(STRING)
        private val BODY =
            MessageCreateParams.Body.builder()
                .maxTokens(LONG)
                .messages(listOf(MESSAGE_PARAM))
                .model(MODEL)
                .build()

        private val NULLABLE_CONTAINER = null
        private val CONTAINER_PARAMS = BetaContainerParams.builder().build()
        private val NULLABLE_CONTEXT_MAN_CONFIG = null

        private val MCP_SERVER =
            BetaRequestMcpServerUrlDefinition.builder().name(STRING).url(STRING).build()
        private val METADATA = BetaMetadata.builder().build()
        private val BETA_OUTPUT_CONFIG = BetaOutputConfig.builder().build()
        private val SERVICE_TIER = MessageCreateParams.ServiceTier.AUTO
        private val SPEED = MessageCreateParams.Speed.FAST
        private val SYSTEM = MessageCreateParams.System.ofString(STRING)
        private val THINKING_CONFIG_ENABLED =
            BetaThinkingConfigEnabled.builder().budgetTokens(LONG).build()
        private val THINKING_CONFIG_DISABLED = BetaThinkingConfigDisabled.builder().build()
        private val THINKING_CONFIG_ADAPTIVE = BetaThinkingConfigAdaptive.builder().build()
        private val THINKING_CONFIG = BetaThinkingConfigParam.ofEnabled(THINKING_CONFIG_ENABLED)

        private val TOOL = toolFromClass(CLASS)
        private val TOOL_UNION = BetaToolUnion.ofBetaTool(TOOL)
        private val TOOL_CHOICE_TOOL = BetaToolChoiceTool.builder().name(STRING).build()
        private val TOOL_CHOICE = BetaToolChoice.ofTool(TOOL_CHOICE_TOOL)
        private val TOOL_CHOICE_ANY = BetaToolChoiceAny.builder().build()
        private val TOOL_CHOICE_AUTO = BetaToolChoiceAuto.builder().build()
        private val TOOL_CHOICE_NONE = BetaToolChoiceNone.builder().build()
        private val TOOL_BASH_20241022 = BetaToolBash20241022.builder().build()
        private val TOOL_BASH_20250124 = BetaToolBash20250124.builder().build()
        private val TOOL_CODE_20250522 = BetaCodeExecutionTool20250522.builder().build()
        private val TOOL_CODE_20250825 = BetaCodeExecutionTool20250825.builder().build()
        private val TOOL_COMP_USE_20241022 =
            BetaToolComputerUse20241022.builder().displayHeightPx(1080).displayWidthPx(1920).build()
        private val TOOL_COMP_USE_20250124 =
            BetaToolComputerUse20250124.builder().displayHeightPx(1080).displayWidthPx(1920).build()
        private val TOOL_MEMORY_TOOL_20250818 = BetaMemoryTool20250818.builder().build()
        private val TOOL_COMP_USE_20251124 =
            BetaToolComputerUse20251124.builder().displayHeightPx(0).displayWidthPx(0).build()
        private val TOOL_SEARCH_TOOL_20251119 =
            BetaToolSearchToolBm25_20251119.builder()
                .type(BetaToolSearchToolBm25_20251119.Type.of(STRING))
                .build()
        private val TOOL_SEARCH_TOOL_REGEX_20251119 =
            BetaToolSearchToolRegex20251119.builder()
                .type(BetaToolSearchToolRegex20251119.Type.of(STRING))
                .build()
        private val BETA_MCP_TOOL_SET = BetaMcpToolset.builder().mcpServerName(STRING).build()
        private val TOOL_TEXT_EDIT_20241022 = BetaToolTextEditor20241022.builder().build()
        private val TOOL_TEXT_EDIT_20250124 = BetaToolTextEditor20250124.builder().build()
        private val TOOL_TEXT_EDIT_20250429 = BetaToolTextEditor20250429.builder().build()
        private val TOOL_TEXT_EDIT_20250728 = BetaToolTextEditor20250728.builder().build()
        private val TOOL_WEB_SEARCH_20250305 = BetaWebSearchTool20250305.builder().build()
        private val TOOL_WEB_FETCH_20250910 = BetaWebFetchTool20250910.builder().build()
        private val TOOL_CODE_20260120 = BetaCodeExecutionTool20260120.builder().build()
        private val TOOL_WEB_SEARCH_20260209 = BetaWebSearchTool20260209.builder().build()
        private val TOOL_WEB_FETCH_20260209 = BetaWebFetchTool20260209.builder().build()

        private val HEADERS = Headers.builder().build()
        private val QUERY_PARAMS = QueryParams.builder().build()

        private val VALIDATION = JsonSchemaLocalValidation.NO

        // The list order follows the declaration order in `MessageCreateParams.Builder` for
        // easier maintenance.
        @JvmStatic
        private fun builderDelegationTestCases() =
            listOf(
                DelegationWriteTestCase("betas", LIST),
                DelegationWriteTestCase("betas", OPTIONAL),
                DelegationWriteTestCase("addBeta", ANTHROPIC_BETA),
                DelegationWriteTestCase("addBeta", STRING),
                DelegationWriteTestCase("body", BODY),
                DelegationWriteTestCase("maxTokens", LONG),
                DelegationWriteTestCase("maxTokens", JSON_FIELD),
                DelegationWriteTestCase("messages", LIST),
                DelegationWriteTestCase("messages", JSON_FIELD),
                DelegationWriteTestCase("addMessage", MESSAGE_PARAM),
                DelegationWriteTestCase("addMessage", MESSAGE),
                DelegationWriteTestCase("addUserMessage", MESSAGE_PARAM_CONTENT),
                DelegationWriteTestCase("addUserMessage", STRING),
                DelegationWriteTestCase("addUserMessageOfBetaContentBlockParams", LIST),
                DelegationWriteTestCase("addAssistantMessage", MESSAGE_PARAM_CONTENT),
                DelegationWriteTestCase("addAssistantMessage", STRING),
                DelegationWriteTestCase("addAssistantMessageOfBetaContentBlockParams", LIST),
                DelegationWriteTestCase("model", MODEL),
                DelegationWriteTestCase("model", JSON_FIELD),
                DelegationWriteTestCase("model", STRING),
                DelegationWriteTestCase("container", NULLABLE_CONTAINER),
                DelegationWriteTestCase("container", OPTIONAL),
                DelegationWriteTestCase("container", JSON_FIELD),
                DelegationWriteTestCase("container", CONTAINER_PARAMS),
                DelegationWriteTestCase("container", STRING),
                DelegationWriteTestCase("contextManagement", NULLABLE_CONTEXT_MAN_CONFIG),
                DelegationWriteTestCase("contextManagement", OPTIONAL),
                DelegationWriteTestCase("contextManagement", JSON_FIELD),
                DelegationWriteTestCase("mcpServers", LIST),
                DelegationWriteTestCase("mcpServers", JSON_FIELD),
                DelegationWriteTestCase("addMcpServer", MCP_SERVER),
                DelegationWriteTestCase("metadata", METADATA),
                DelegationWriteTestCase("metadata", JSON_FIELD),
                DelegationWriteTestCase("outputConfig", BETA_OUTPUT_CONFIG),
                DelegationWriteTestCase("outputConfig", JSON_FIELD),
                // `outputFormat` is a special case that is tested separately.
                DelegationWriteTestCase("serviceTier", SERVICE_TIER),
                DelegationWriteTestCase("serviceTier", JSON_FIELD),
                DelegationWriteTestCase("speed", SPEED),
                DelegationWriteTestCase("speed", OPTIONAL),
                DelegationWriteTestCase("speed", JSON_FIELD),
                DelegationWriteTestCase("stopSequences", LIST),
                DelegationWriteTestCase("stopSequences", JSON_FIELD),
                DelegationWriteTestCase("addStopSequence", STRING),
                DelegationWriteTestCase("system", SYSTEM),
                DelegationWriteTestCase("system", JSON_FIELD),
                DelegationWriteTestCase("system", STRING),
                DelegationWriteTestCase("systemOfBetaTextBlockParams", LIST),
                DelegationWriteTestCase("temperature", DOUBLE),
                DelegationWriteTestCase("temperature", JSON_FIELD),
                DelegationWriteTestCase("inferenceGeo", STRING),
                DelegationWriteTestCase("inferenceGeo", OPTIONAL),
                DelegationWriteTestCase("inferenceGeo", JSON_FIELD),
                DelegationWriteTestCase("thinking", THINKING_CONFIG),
                DelegationWriteTestCase("thinking", JSON_FIELD),
                DelegationWriteTestCase("thinking", THINKING_CONFIG_ENABLED),
                DelegationWriteTestCase("enabledThinking", LONG),
                DelegationWriteTestCase("thinking", THINKING_CONFIG_DISABLED),
                DelegationWriteTestCase("thinking", THINKING_CONFIG_ADAPTIVE),
                DelegationWriteTestCase("toolChoice", TOOL_CHOICE),
                DelegationWriteTestCase("toolChoice", JSON_FIELD),
                DelegationWriteTestCase("toolChoice", TOOL_CHOICE_AUTO),
                DelegationWriteTestCase("toolChoice", TOOL_CHOICE_ANY),
                DelegationWriteTestCase("toolChoice", TOOL_CHOICE_TOOL),
                DelegationWriteTestCase("toolToolChoice", STRING),
                DelegationWriteTestCase("toolChoice", TOOL_CHOICE_NONE),
                DelegationWriteTestCase("tools", LIST),
                DelegationWriteTestCase("tools", JSON_FIELD),
                DelegationWriteTestCase("addTool", TOOL_UNION),
                DelegationWriteTestCase("addTool", TOOL),
                DelegationWriteTestCase("addTool", TOOL_BASH_20241022),
                DelegationWriteTestCase("addTool", TOOL_BASH_20250124),
                DelegationWriteTestCase("addTool", TOOL_CODE_20250522),
                DelegationWriteTestCase("addTool", TOOL_CODE_20250825),
                DelegationWriteTestCase("addTool", TOOL_COMP_USE_20241022),
                DelegationWriteTestCase("addTool", TOOL_MEMORY_TOOL_20250818),
                DelegationWriteTestCase("addTool", TOOL_COMP_USE_20251124),
                DelegationWriteTestCase("addTool", TOOL_COMP_USE_20250124),
                DelegationWriteTestCase("addTool", TOOL_TEXT_EDIT_20241022),
                DelegationWriteTestCase("addTool", TOOL_TEXT_EDIT_20250124),
                DelegationWriteTestCase("addTool", TOOL_TEXT_EDIT_20250429),
                DelegationWriteTestCase("addTool", TOOL_TEXT_EDIT_20250728),
                DelegationWriteTestCase("addTool", TOOL_WEB_SEARCH_20250305),
                DelegationWriteTestCase("addTool", TOOL_WEB_FETCH_20250910),
                DelegationWriteTestCase("addTool", TOOL_CODE_20260120),
                DelegationWriteTestCase("addTool", TOOL_WEB_SEARCH_20260209),
                DelegationWriteTestCase("addTool", TOOL_WEB_FETCH_20260209),
                DelegationWriteTestCase("addTool", TOOL_SEARCH_TOOL_20251119),
                DelegationWriteTestCase("addTool", TOOL_SEARCH_TOOL_REGEX_20251119),
                DelegationWriteTestCase("addTool", BETA_MCP_TOOL_SET),
                DelegationWriteTestCase("addTool", CLASS, VALIDATION),
                DelegationWriteTestCase("topK", LONG),
                DelegationWriteTestCase("topK", JSON_FIELD),
                DelegationWriteTestCase("topP", DOUBLE),
                DelegationWriteTestCase("topP", JSON_FIELD),
                DelegationWriteTestCase("additionalBodyProperties", MAP),
                DelegationWriteTestCase("putAdditionalBodyProperty", STRING, JSON_VALUE),
                DelegationWriteTestCase("putAllAdditionalBodyProperties", MAP),
                DelegationWriteTestCase("removeAdditionalBodyProperty", STRING),
                DelegationWriteTestCase("removeAllAdditionalBodyProperties", SET),
                DelegationWriteTestCase("additionalHeaders", HEADERS),
                DelegationWriteTestCase("additionalHeaders", MAP),
                DelegationWriteTestCase("putAdditionalHeader", STRING, STRING),
                DelegationWriteTestCase("putAdditionalHeaders", STRING, LIST),
                DelegationWriteTestCase("putAllAdditionalHeaders", HEADERS),
                DelegationWriteTestCase("putAllAdditionalHeaders", MAP),
                DelegationWriteTestCase("replaceAdditionalHeaders", STRING, STRING),
                DelegationWriteTestCase("replaceAdditionalHeaders", STRING, LIST),
                DelegationWriteTestCase("replaceAllAdditionalHeaders", HEADERS),
                DelegationWriteTestCase("replaceAllAdditionalHeaders", MAP),
                DelegationWriteTestCase("removeAdditionalHeaders", STRING),
                DelegationWriteTestCase("removeAllAdditionalHeaders", SET),
                DelegationWriteTestCase("additionalQueryParams", QUERY_PARAMS),
                DelegationWriteTestCase("additionalQueryParams", MAP),
                DelegationWriteTestCase("putAdditionalQueryParam", STRING, STRING),
                DelegationWriteTestCase("putAdditionalQueryParams", STRING, LIST),
                DelegationWriteTestCase("putAllAdditionalQueryParams", QUERY_PARAMS),
                DelegationWriteTestCase("putAllAdditionalQueryParams", MAP),
                DelegationWriteTestCase("replaceAdditionalQueryParams", STRING, STRING),
                DelegationWriteTestCase("replaceAdditionalQueryParams", STRING, LIST),
                DelegationWriteTestCase("replaceAllAdditionalQueryParams", QUERY_PARAMS),
                DelegationWriteTestCase("replaceAllAdditionalQueryParams", MAP),
                DelegationWriteTestCase("removeAdditionalQueryParams", STRING),
                DelegationWriteTestCase("removeAllAdditionalQueryParams", SET),
            )
    }

    // New instances of the `mockBuilderDelegate` and `builderDelegator` are required for each test
    // case (each test case runs in its own instance of the test class).
    private val mockBuilderDelegate: MessageCreateParams.Builder =
        mock(MessageCreateParams.Builder::class.java)
    private val builderDelegator =
        StructuredMessageCreateParams.builder<X>().inject(mockBuilderDelegate)

    @Test
    fun allBuilderDelegateFunctionsExistInDelegator() {
        // The delegator class does not implement the various `outputFormat` functions of the
        // delegate class.
        checkAllDelegation(mockBuilderDelegate::class, builderDelegator::class, "outputFormat")
    }

    @Test
    fun allBuilderDelegatorFunctionsExistInDelegate() {
        // The delegator implements a different `outputFormat` function from those overloads in
        // the delegate class.
        checkAllDelegation(builderDelegator::class, mockBuilderDelegate::class, "outputFormat")
    }

    @Test
    fun allBuilderDelegatorFunctionsAreTested() {
        checkAllDelegatorWriteFunctionsAreTested(
            builderDelegator::class,
            builderDelegationTestCases(),
            // These functions have non-standard delegation and are tested separately below.
            exceptionalTestedFns = listOf("outputFormat", "outputConfig"),
            nonDelegatingFns = setOf("build", "wrap", "inject"),
        )
    }

    @ParameterizedTest
    @MethodSource("builderDelegationTestCases")
    fun `delegation of Builder write functions`(testCase: DelegationWriteTestCase) {
        checkOneDelegationWrite(builderDelegator, mockBuilderDelegate, testCase)
    }

    @Test
    fun `delegation of outputFormat`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method. The deprecated `outputFormat` now calls `outputConfig` and `addBeta`
        // internally instead of the delegate's `outputFormat`.
        val delegatorTestCase = DelegationWriteTestCase("outputFormat", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        @Suppress("DEPRECATION")
        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that outputConfig and addBeta were called on the mock delegate.
        val expectedFormat = betaOutputFormatFromClass(X::class.java)
        val expectedOutputConfig = BetaOutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1))
            .addBeta(AnthropicBeta.of("structured-outputs-2025-12-15"))
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class`() {
        // Special unit test case for the outputConfig(Class<T>, ...) overload which has
        // non-standard delegation behavior similar to outputFormat.
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that outputConfig and addBeta were called on the mock delegate.
        val expectedFormat = betaOutputFormatFromClass(X::class.java)
        val expectedOutputConfig = BetaOutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1))
            .addBeta(AnthropicBeta.of("structured-outputs-2025-12-15"))
        verifyNoMoreInteractions(mockBuilderDelegate)
    }
}
