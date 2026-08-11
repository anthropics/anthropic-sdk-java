package com.anthropic.models.beta.messages

import com.anthropic.core.CLASS
import com.anthropic.core.DOUBLE
import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JSON_FIELD
import com.anthropic.core.JSON_VALUE
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
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
        private val MODEL = Model.CLAUDE_SONNET_4_5
        private val ANTHROPIC_BETA = AnthropicBeta.TOKEN_COUNTING_2024_11_01

        private val MESSAGE =
            BetaMessage.builder()
                .id(STRING)
                .container(null)
                .content(listOf())
                .model(MODEL)
                .stopDetails(null)
                .stopReason(BetaStopReason.STOP_SEQUENCE)
                .stopSequence(null)
                .usage(
                    BetaUsage.builder()
                        .cacheCreation(null)
                        .cacheCreationInputTokens(null)
                        .cacheReadInputTokens(null)
                        .fallbackCredit(null)
                        .inferenceGeo("inference_geo")
                        .inputTokens(LONG)
                        .iterations(null)
                        .outputTokens(LONG)
                        .outputTokensDetails(
                            BetaOutputTokensDetails.builder().thinkingTokens(0L).build()
                        )
                        .serverToolUse(null)
                        .serviceTier(null)
                        .speed(null)
                        .build()
                )
                .contextManagement(null)
                .diagnostics(null)
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

        private val NULLABLE_CACHE_CONTROL = null
        private val NULLABLE_CONTAINER = null
        private val CONTAINER_PARAMS = BetaContainerParams.builder().build()
        private val NULLABLE_CONTEXT_MAN_CONFIG = null
        private val NULLABLE_DIAGNOSTICS = null
        private val NULLABLE_FALLBACK_CREDIT_TOKEN = null
        private val FALLBACK_CREDIT_TOKEN_PARAM =
            BetaFallbackCreditTokenParam.builder().token(STRING).build()
        private val NULLABLE_FALLBACKS = null

        private val MCP_SERVER =
            BetaRequestMcpServerUrlDefinition.builder().name(STRING).url(STRING).build()
        private val METADATA = BetaMetadata.builder().build()
        private val OUTPUT_CONFIG_WITH_EFFORT =
            BetaOutputConfig.builder()
                .effort(BetaOutputConfig.Effort.HIGH)
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .build()
        private val OTHER_FORMAT =
            BetaJsonOutputFormat.of(
                BetaJsonOutputFormat.Schema.builder()
                    .putAdditionalProperty("type", JSON_VALUE)
                    .build()
            )
        private val OUTPUT_CONFIG_WITH_FORMAT =
            BetaOutputConfig.builder().format(OTHER_FORMAT).build()
        private val STRUCTURED_OUTPUTS_BETA = AnthropicBeta.of("structured-outputs-2025-12-15")
        private val SERVICE_TIER = MessageCreateParams.ServiceTier.AUTO
        private val SPEED = MessageCreateParams.Speed.FAST
        private val SYSTEM = MessageCreateParams.System.ofString(STRING)
        private val THINKING_CONFIG_ENABLED =
            BetaThinkingConfigEnabled.builder().budgetTokens(LONG).build()
        private val THINKING_CONFIG_DISABLED = BetaThinkingConfigDisabled.builder().build()
        private val THINKING_CONFIG_ADAPTIVE = BetaThinkingConfigAdaptive.builder().build()
        private val THINKING_CONFIG = BetaThinkingConfigParam.ofEnabled(THINKING_CONFIG_ENABLED)

        private val TOOL = toolFromClass(CLASS)
        private val MCP_BETA_TOOL =
            com.anthropic.helpers.McpBetaTool.builder()
                .name(TOOL.name())
                .definition(TOOL)
                .runner { BetaToolResultBlockParam.Content.ofString(it) }
                .build()
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
        private val TOOL_CODE_20260521 = BetaCodeExecutionTool20260521.builder().build()
        private val TOOL_WEB_SEARCH_20260209 = BetaWebSearchTool20260209.builder().build()
        private val TOOL_WEB_FETCH_20260209 = BetaWebFetchTool20260209.builder().build()
        private val TOOL_WEB_FETCH_20260309 = BetaWebFetchTool20260309.builder().build()
        private val TOOL_WEB_SEARCH_20260318 = BetaWebSearchTool20260318.builder().build()
        private val TOOL_WEB_FETCH_20260318 = BetaWebFetchTool20260318.builder().build()
        private val TOOL_ADVISOR_20260301 = BetaAdvisorTool20260301.builder().model(MODEL).build()

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
                DelegationWriteTestCase("addSystemMessage", MESSAGE_PARAM_CONTENT),
                DelegationWriteTestCase("addSystemMessage", STRING),
                DelegationWriteTestCase("addSystemMessageOfBetaContentBlockParams", LIST),
                DelegationWriteTestCase("addAssistantMessage", MESSAGE_PARAM_CONTENT),
                DelegationWriteTestCase("addAssistantMessage", STRING),
                DelegationWriteTestCase("addAssistantMessageOfBetaContentBlockParams", LIST),
                DelegationWriteTestCase("model", MODEL),
                DelegationWriteTestCase("model", JSON_FIELD),
                DelegationWriteTestCase("model", STRING),
                DelegationWriteTestCase("cacheControl", NULLABLE_CACHE_CONTROL),
                DelegationWriteTestCase("cacheControl", OPTIONAL),
                DelegationWriteTestCase("cacheControl", JSON_FIELD),
                DelegationWriteTestCase("container", NULLABLE_CONTAINER),
                DelegationWriteTestCase("container", OPTIONAL),
                DelegationWriteTestCase("container", JSON_FIELD),
                DelegationWriteTestCase("container", CONTAINER_PARAMS),
                DelegationWriteTestCase("container", STRING),
                DelegationWriteTestCase("contextManagement", NULLABLE_CONTEXT_MAN_CONFIG),
                DelegationWriteTestCase("contextManagement", OPTIONAL),
                DelegationWriteTestCase("contextManagement", JSON_FIELD),
                DelegationWriteTestCase("diagnostics", NULLABLE_DIAGNOSTICS),
                DelegationWriteTestCase("diagnostics", OPTIONAL),
                DelegationWriteTestCase("diagnostics", JSON_FIELD),
                DelegationWriteTestCase("fallbackCreditToken", NULLABLE_FALLBACK_CREDIT_TOKEN),
                DelegationWriteTestCase("fallbackCreditToken", OPTIONAL),
                DelegationWriteTestCase("fallbackCreditToken", JSON_FIELD),
                DelegationWriteTestCase("fallbackCreditToken", STRING),
                DelegationWriteTestCase("fallbackCreditToken", FALLBACK_CREDIT_TOKEN_PARAM),
                DelegationWriteTestCase("fallbacks", NULLABLE_FALLBACKS),
                DelegationWriteTestCase("fallbacks", OPTIONAL),
                DelegationWriteTestCase("fallbacks", JSON_FIELD),
                DelegationWriteTestCase("fallbacksOfFallbackParams", LIST),
                DelegationWriteTestCase("fallbacksDefault"),
                DelegationWriteTestCase("mcpServers", LIST),
                DelegationWriteTestCase("mcpServers", JSON_FIELD),
                DelegationWriteTestCase("addMcpServer", MCP_SERVER),
                DelegationWriteTestCase("metadata", METADATA),
                DelegationWriteTestCase("metadata", JSON_FIELD),
                // `outputConfig` and `outputFormat` are special cases that are tested separately.
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
                DelegationWriteTestCase("addTool", TOOL_CODE_20260521),
                DelegationWriteTestCase("addTool", TOOL_WEB_SEARCH_20260209),
                DelegationWriteTestCase("addTool", TOOL_WEB_FETCH_20260209),
                DelegationWriteTestCase("addTool", TOOL_WEB_FETCH_20260309),
                DelegationWriteTestCase("addTool", TOOL_WEB_SEARCH_20260318),
                DelegationWriteTestCase("addTool", TOOL_WEB_FETCH_20260318),
                DelegationWriteTestCase("addTool", TOOL_ADVISOR_20260301),
                DelegationWriteTestCase("addTool", TOOL_SEARCH_TOOL_20251119),
                DelegationWriteTestCase("addTool", TOOL_SEARCH_TOOL_REGEX_20251119),
                DelegationWriteTestCase("addTool", BETA_MCP_TOOL_SET),
                DelegationWriteTestCase("addTool", CLASS, VALIDATION),
                DelegationWriteTestCase("addTool", MCP_BETA_TOOL),
                DelegationWriteTestCase("addTools", listOf(MCP_BETA_TOOL)),
                DelegationWriteTestCase("topK", LONG),
                DelegationWriteTestCase("topK", JSON_FIELD),
                DelegationWriteTestCase("topP", DOUBLE),
                DelegationWriteTestCase("topP", JSON_FIELD),
                DelegationWriteTestCase("userProfileId", STRING),
                DelegationWriteTestCase("userProfileId", OPTIONAL),
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
        StructuredMessageCreateParams.builder<X>().wrap(mockBuilderDelegate)

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
            // These functions merge with the delegate's current output config, so they have
            // non-standard delegation and are tested separately below.
            exceptionalTestedFns =
                listOf(
                    "outputFormat",
                    "outputConfig",
                    "outputConfig",
                    "outputConfig",
                    "outputConfig",
                    "outputConfig",
                ),
            nonDelegatingFns = setOf("build", "wrap", "mergeOutputFormat"),
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

        // Verify that the current output config was read and then set with the derived format.
        val expectedFormat = betaOutputFormatFromClass(X::class.java)
        val expectedOutputConfig = BetaOutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class`() {
        // Special unit test case for the outputConfig(Class<T>, ...) overload which has
        // non-standard delegation behavior similar to outputFormat.
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that the current output config was read and then set with the derived format.
        val expectedFormat = betaOutputFormatFromClass(X::class.java)
        val expectedOutputConfig = BetaOutputConfig.builder().format(expectedFormat).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class merges into current output config`() {
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(
                OUTPUT_CONFIG_WITH_FORMAT.toBuilder()
                    .taskBudget(BetaTokenTaskBudget.of(LONG))
                    .build()
            )

        builderDelegator.outputConfig(X::class.java)

        // Only the format of the current output config is replaced.
        val expectedOutputConfig =
            BetaOutputConfig.builder()
                .format(betaOutputFormatFromClass(X::class.java))
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with Class and effort`() {
        // Special unit test case for the deprecated outputConfig(Class<T>, Effort, ...) overload
        // which must set the effort alongside the derived format on the current output config.
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(
                OUTPUT_CONFIG_WITH_EFFORT.toBuilder().effort(BetaOutputConfig.Effort.LOW).build()
            )

        @Suppress("DEPRECATION")
        builderDelegator.outputConfig(X::class.java, BetaOutputConfig.Effort.HIGH, VALIDATION)

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder()
                .format(betaOutputFormatFromClass(X::class.java, VALIDATION))
                .build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with StructuredOutputConfig`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method: it unwraps a `StructuredOutputConfig` to its raw `BetaOutputConfig`.
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .format(X::class.java)
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .build()
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", structuredOutputConfig)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        delegatorMethod.invoke(builderDelegator, delegatorTestCase.inputValues[0])

        // Verify that outputConfig and addBeta were called on the mock delegate.
        verify(mockBuilderDelegate, times(1)).outputConfig(structuredOutputConfig.rawOutputConfig)
        verify(mockBuilderDelegate, times(1)).addBeta(STRUCTURED_OUTPUTS_BETA)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with BetaOutputConfig`() {
        // With no current output config, the given output config is passed through verbatim.
        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_EFFORT)

        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with BetaOutputConfig carries over current format`() {
        `when`(mockBuilderDelegate.currentOutputConfig()).thenReturn(OUTPUT_CONFIG_WITH_FORMAT)

        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_EFFORT)

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder().format(OTHER_FORMAT).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with BetaOutputConfig keeps given format`() {
        `when`(mockBuilderDelegate.currentOutputConfig())
            .thenReturn(
                BetaOutputConfig.builder().format(betaOutputFormatFromClass(X::class.java)).build()
            )

        builderDelegator.outputConfig(OUTPUT_CONFIG_WITH_FORMAT)

        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(OUTPUT_CONFIG_WITH_FORMAT)
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `delegation of outputConfig with JsonField`() {
        // A known value is merged like a `BetaOutputConfig`; any other JSON field is passed
        // verbatim.
        `when`(mockBuilderDelegate.currentOutputConfig()).thenReturn(OUTPUT_CONFIG_WITH_FORMAT)

        builderDelegator.outputConfig(JsonField.of(OUTPUT_CONFIG_WITH_EFFORT))
        builderDelegator.outputConfig(JsonMissing.of())

        val expectedOutputConfig =
            OUTPUT_CONFIG_WITH_EFFORT.toBuilder().format(OTHER_FORMAT).build()
        verify(mockBuilderDelegate, times(1)).currentOutputConfig()
        verify(mockBuilderDelegate, times(1)).outputConfig(expectedOutputConfig)
        verify(mockBuilderDelegate, times(1)).outputConfig(JsonMissing.of())
        verifyNoMoreInteractions(mockBuilderDelegate)
    }

    @Test
    fun `outputConfig with StructuredOutputConfig preserves other output options`() {
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .taskBudget(BetaTokenTaskBudget.of(LONG))
                .format(X::class.java)
                .build()

        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(structuredOutputConfig)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(structuredOutputConfig.rawOutputConfig)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        // The beta header is injected automatically.
        assertThat(params.rawParams.betas()).hasValue(listOf(STRUCTURED_OUTPUTS_BETA))
    }

    @Test
    fun `outputConfig with StructuredOutputConfig replaces the output config`() {
        val structuredOutputConfig =
            StructuredOutputConfig.builder<X>()
                .effort(BetaOutputConfig.Effort.HIGH)
                .format(X::class.java)
                .build()

        @Suppress("DEPRECATION")
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputFormat(X::class.java)
                .outputConfig(X::class.java)
                .outputConfig(structuredOutputConfig)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(structuredOutputConfig.rawOutputConfig)
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `outputConfig with class after output config with effort keeps both`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputConfig(X::class.java)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `deprecated outputConfig with class and effort after output config keeps task budget`() {
        @Suppress("DEPRECATION")
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .outputConfig(X::class.java, BetaOutputConfig.Effort.LOW)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.LOW)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `output config with effort after outputConfig with class keeps both`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(X::class.java)
                .outputConfig(OUTPUT_CONFIG_WITH_EFFORT)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.HIGH)
        assertThat(params.rawParams.outputConfig().flatMap { it.taskBudget() })
            .hasValue(BetaTokenTaskBudget.of(LONG))
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
        assertThat(params.rawParams.betas().get()).contains(STRUCTURED_OUTPUTS_BETA)
    }

    @Test
    fun `outputConfig with class after output config with format replaces the format`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(
                    OUTPUT_CONFIG_WITH_FORMAT.toBuilder()
                        .effort(BetaOutputConfig.Effort.LOW)
                        .build()
                )
                .outputConfig(X::class.java)
                .build()

        assertThat(params.rawParams.outputConfig().flatMap { it.effort() })
            .hasValue(BetaOutputConfig.Effort.LOW)
        assertThat(params.rawParams.outputConfig().flatMap { it.format() })
            .hasValue(betaOutputFormatFromClass(X::class.java))
    }

    @Test
    fun `output config with format after outputConfig with class replaces the format`() {
        val params =
            MessageCreateParams.builder()
                .maxTokens(LONG)
                .model(MODEL)
                .addUserMessage(STRING)
                .outputConfig(X::class.java)
                .outputConfig(OUTPUT_CONFIG_WITH_FORMAT)
                .build()

        assertThat(params.outputType).isEqualTo(X::class.java)
        assertThat(params.rawParams.outputConfig()).hasValue(OUTPUT_CONFIG_WITH_FORMAT)
    }
}
