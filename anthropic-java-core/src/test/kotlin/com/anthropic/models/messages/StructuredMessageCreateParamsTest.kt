package com.anthropic.models.messages

import com.anthropic.core.DelegationWriteTestCase
import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.core.X
import com.anthropic.core.checkAllDelegation
import com.anthropic.core.checkAllDelegatorWriteFunctionsAreTested
import com.anthropic.core.checkOneDelegationWrite
import com.anthropic.core.findDelegationMethod
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.outputFormatFromClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

/**
 * Unit tests for the GA [StructuredMessageCreateParams.Builder] class (delegator) and its
 * delegation to a wrapped [MessageCreateParams.Builder] (delegate).
 */
internal class StructuredMessageCreateParamsTest {

    companion object {
        private val BODY =
            MessageCreateParams.Body.builder()
                .maxTokens(1024)
                .messages(emptyList())
                .model(Model.CLAUDE_4_SONNET_20250514)
                .build()

        @JvmStatic
        private fun builderDelegationTestCases() =
            listOf(
                DelegationWriteTestCase("body", BODY),
                DelegationWriteTestCase("maxTokens", 1234L),
                DelegationWriteTestCase("maxTokens", JsonField.of(1234L)),
                DelegationWriteTestCase("messages", emptyList<MessageParam>()),
                DelegationWriteTestCase("messages", JsonField.of(emptyList<MessageParam>())),
                DelegationWriteTestCase(
                    "addMessage",
                    MessageParam.builder().role(MessageParam.Role.USER).content("hi").build(),
                ),
                DelegationWriteTestCase(
                    "addMessage",
                    Message.builder()
                        .id("msg_1")
                        .content(emptyList())
                        .model(Model.CLAUDE_4_SONNET_20250514)
                        .stopReason(StopReason.END_TURN)
                        .stopSequence(null)
                        .usage(
                            Usage.builder()
                                .inputTokens(100)
                                .outputTokens(200)
                                .cacheCreation(null)
                                .cacheCreationInputTokens(null)
                                .cacheReadInputTokens(null)
                                .serverToolUse(null)
                                .serviceTier(null)
                                .inferenceGeo(null)
                                .build()
                        )
                        .build(),
                ),
                DelegationWriteTestCase("addUserMessage", MessageParam.Content.ofString("Hello")),
                DelegationWriteTestCase("addUserMessage", "Hello"),
                DelegationWriteTestCase(
                    "addUserMessageOfBlockParams",
                    emptyList<ContentBlockParam>(),
                ),
                DelegationWriteTestCase("addAssistantMessage", MessageParam.Content.ofString("Hi")),
                DelegationWriteTestCase("addAssistantMessage", "Hi"),
                DelegationWriteTestCase(
                    "addAssistantMessageOfBlockParams",
                    emptyList<ContentBlockParam>(),
                ),
                DelegationWriteTestCase("model", Model.CLAUDE_4_SONNET_20250514),
                DelegationWriteTestCase("model", JsonField.of(Model.CLAUDE_4_SONNET_20250514)),
                DelegationWriteTestCase("model", "claude-4-sonnet-20250514"),
                DelegationWriteTestCase("cacheControl", null as CacheControlEphemeral?),
                DelegationWriteTestCase(
                    "cacheControl",
                    java.util.Optional.of(CacheControlEphemeral.builder().build()),
                ),
                DelegationWriteTestCase(
                    "cacheControl",
                    JsonField.of(CacheControlEphemeral.builder().build()),
                ),
                DelegationWriteTestCase("metadata", Metadata.builder().build()),
                DelegationWriteTestCase("metadata", JsonField.of(Metadata.builder().build())),
                DelegationWriteTestCase("outputConfig", OutputConfig.builder().build()),
                DelegationWriteTestCase(
                    "outputConfig",
                    JsonField.of(OutputConfig.builder().build()),
                ),
                DelegationWriteTestCase("serviceTier", MessageCreateParams.ServiceTier.AUTO),
                DelegationWriteTestCase(
                    "serviceTier",
                    JsonField.of(MessageCreateParams.ServiceTier.AUTO),
                ),
                DelegationWriteTestCase("stopSequences", listOf("stop")),
                DelegationWriteTestCase("stopSequences", JsonField.of(listOf("stop"))),
                DelegationWriteTestCase("addStopSequence", "stop"),
                DelegationWriteTestCase("system", MessageCreateParams.System.ofString("system")),
                DelegationWriteTestCase(
                    "system",
                    JsonField.of(MessageCreateParams.System.ofString("system")),
                ),
                DelegationWriteTestCase("system", "system"),
                DelegationWriteTestCase("systemOfTextBlockParams", emptyList<TextBlockParam>()),
                DelegationWriteTestCase("temperature", 0.5),
                DelegationWriteTestCase("temperature", JsonField.of(0.5)),
                DelegationWriteTestCase(
                    "thinking",
                    ThinkingConfigParam.ofEnabled(
                        ThinkingConfigEnabled.builder().budgetTokens(1024).build()
                    ),
                ),
                DelegationWriteTestCase(
                    "thinking",
                    JsonField.of(
                        ThinkingConfigParam.ofEnabled(
                            ThinkingConfigEnabled.builder().budgetTokens(1024).build()
                        )
                    ),
                ),
                DelegationWriteTestCase(
                    "thinking",
                    ThinkingConfigEnabled.builder().budgetTokens(1024).build(),
                ),
                DelegationWriteTestCase("enabledThinking", 1024L),
                DelegationWriteTestCase("thinking", ThinkingConfigDisabled.builder().build()),
                DelegationWriteTestCase("thinking", ThinkingConfigAdaptive.builder().build()),
                DelegationWriteTestCase(
                    "toolChoice",
                    ToolChoice.ofAuto(ToolChoiceAuto.builder().build()),
                ),
                DelegationWriteTestCase(
                    "toolChoice",
                    JsonField.of(ToolChoice.ofAuto(ToolChoiceAuto.builder().build())),
                ),
                DelegationWriteTestCase("toolChoice", ToolChoiceAuto.builder().build()),
                DelegationWriteTestCase("toolChoice", ToolChoiceAny.builder().build()),
                DelegationWriteTestCase(
                    "toolChoice",
                    ToolChoiceTool.builder().name("test").build(),
                ),
                DelegationWriteTestCase("toolToolChoice", "test"),
                DelegationWriteTestCase("toolChoice", ToolChoiceNone.builder().build()),
                DelegationWriteTestCase("tools", emptyList<ToolUnion>()),
                DelegationWriteTestCase("tools", JsonField.of(emptyList<ToolUnion>())),
                DelegationWriteTestCase(
                    "addTool",
                    ToolUnion.ofTool(
                        Tool.builder()
                            .name("test")
                            .inputSchema(JsonValue.from(mapOf<String, Any>()))
                            .build()
                    ),
                ),
                DelegationWriteTestCase(
                    "addTool",
                    Tool.builder()
                        .name("test")
                        .inputSchema(JsonValue.from(mapOf<String, Any>()))
                        .build(),
                ),
                DelegationWriteTestCase("addTool", ToolBash20250124.builder().build()),
                DelegationWriteTestCase("addTool", ToolTextEditor20250124.builder().build()),
                DelegationWriteTestCase("addTool", ToolTextEditor20250429.builder().build()),
                DelegationWriteTestCase("addTool", ToolTextEditor20250728.builder().build()),
                DelegationWriteTestCase("addTool", WebSearchTool20250305.builder().build()),
                DelegationWriteTestCase("addTool", WebFetchTool20250910.builder().build()),
                DelegationWriteTestCase("addTool", CodeExecutionTool20250522.builder().build()),
                DelegationWriteTestCase("addTool", CodeExecutionTool20250825.builder().build()),
                DelegationWriteTestCase("addTool", CodeExecutionTool20260120.builder().build()),
                DelegationWriteTestCase("addTool", MemoryTool20250818.builder().build()),
                DelegationWriteTestCase("addTool", WebSearchTool20260209.builder().build()),
                DelegationWriteTestCase("addTool", WebFetchTool20260209.builder().build()),
                DelegationWriteTestCase(
                    "addTool",
                    ToolSearchToolBm25_20251119.builder()
                        .type(ToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                        .build(),
                ),
                DelegationWriteTestCase(
                    "addTool",
                    ToolSearchToolRegex20251119.builder()
                        .type(ToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                        .build(),
                ),
                DelegationWriteTestCase("container", null as String?),
                DelegationWriteTestCase("container", java.util.Optional.of("test")),
                DelegationWriteTestCase("container", JsonField.of("test")),
                DelegationWriteTestCase("topK", 10L),
                DelegationWriteTestCase("topK", JsonField.of(10L)),
                DelegationWriteTestCase("topP", 0.9),
                DelegationWriteTestCase("topP", JsonField.of(0.9)),
                DelegationWriteTestCase(
                    "additionalBodyProperties",
                    mapOf("k" to JsonValue.from("v")),
                ),
                DelegationWriteTestCase("putAdditionalBodyProperty", "k", JsonValue.from("v")),
                DelegationWriteTestCase(
                    "putAllAdditionalBodyProperties",
                    mapOf("k" to JsonValue.from("v")),
                ),
                DelegationWriteTestCase("inferenceGeo", "test"),
                DelegationWriteTestCase("inferenceGeo", JsonField.of("test")),
                DelegationWriteTestCase("inferenceGeo", java.util.Optional.of("test")),
                DelegationWriteTestCase("removeAdditionalBodyProperty", "k"),
                DelegationWriteTestCase("removeAllAdditionalBodyProperties", setOf("k")),
                DelegationWriteTestCase("additionalHeaders", Headers.builder().build()),
                DelegationWriteTestCase("additionalHeaders", mapOf("k" to listOf("v"))),
                DelegationWriteTestCase("putAdditionalHeader", "k", "v"),
                DelegationWriteTestCase("putAdditionalHeaders", "k", listOf("v")),
                DelegationWriteTestCase("putAllAdditionalHeaders", Headers.builder().build()),
                DelegationWriteTestCase("putAllAdditionalHeaders", mapOf("k" to listOf("v"))),
                DelegationWriteTestCase("replaceAdditionalHeaders", "k", "v"),
                DelegationWriteTestCase("replaceAdditionalHeaders", "k", listOf("v")),
                DelegationWriteTestCase("replaceAllAdditionalHeaders", Headers.builder().build()),
                DelegationWriteTestCase("replaceAllAdditionalHeaders", mapOf("k" to listOf("v"))),
                DelegationWriteTestCase("removeAdditionalHeaders", "k"),
                DelegationWriteTestCase("removeAllAdditionalHeaders", setOf("k")),
                DelegationWriteTestCase("additionalQueryParams", QueryParams.builder().build()),
                DelegationWriteTestCase("additionalQueryParams", mapOf("k" to listOf("v"))),
                DelegationWriteTestCase("putAdditionalQueryParam", "k", "v"),
                DelegationWriteTestCase("putAdditionalQueryParams", "k", listOf("v")),
                DelegationWriteTestCase(
                    "putAllAdditionalQueryParams",
                    QueryParams.builder().build(),
                ),
                DelegationWriteTestCase("putAllAdditionalQueryParams", mapOf("k" to listOf("v"))),
                DelegationWriteTestCase("replaceAdditionalQueryParams", "k", "v"),
                DelegationWriteTestCase("replaceAdditionalQueryParams", "k", listOf("v")),
                DelegationWriteTestCase(
                    "replaceAllAdditionalQueryParams",
                    QueryParams.builder().build(),
                ),
                DelegationWriteTestCase(
                    "replaceAllAdditionalQueryParams",
                    mapOf("k" to listOf("v")),
                ),
                DelegationWriteTestCase("removeAdditionalQueryParams", "k"),
                DelegationWriteTestCase("removeAllAdditionalQueryParams", setOf("k")),
            )
    }

    private val mockBuilderDelegate: MessageCreateParams.Builder =
        mock(MessageCreateParams.Builder::class.java)
    private val builderDelegator =
        StructuredMessageCreateParams.builder<X>().inject(mockBuilderDelegate)

    @Test
    fun allBuilderDelegateFunctionsExistInDelegator() {
        // The delegator doesn't implement outputConfig(Class) method - it returns a different
        // builder
        checkAllDelegation(
            mockBuilderDelegate::class,
            builderDelegator::class,
            "outputConfig",
            "from",
        )
    }

    @Test
    fun allBuilderDelegatorFunctionsExistInDelegate() {
        // The delegator has some methods that don't exist in the delegate
        checkAllDelegation(builderDelegator::class, mockBuilderDelegate::class, "outputConfig")
    }

    @Test
    fun allBuilderDelegatorFunctionsAreTested() {
        checkAllDelegatorWriteFunctionsAreTested(
            builderDelegator::class,
            builderDelegationTestCases(),
            exceptionalTestedFns = listOf("outputConfig"),
            nonDelegatingFns = setOf("build", "wrap", "inject"),
        )
    }

    @ParameterizedTest
    @MethodSource("builderDelegationTestCases")
    fun `delegation of Builder write functions`(testCase: DelegationWriteTestCase) {
        checkOneDelegationWrite(builderDelegator, mockBuilderDelegate, testCase)
    }

    @Test
    fun `delegation of outputConfig with class`() {
        // Special unit test case as the delegator method signature does not match that of the
        // delegate method - it converts a Class to an OutputConfig with JsonOutputFormat
        val delegatorTestCase = DelegationWriteTestCase("outputConfig", X::class.java)
        val delegatorMethod = findDelegationMethod(builderDelegator, delegatorTestCase)

        // Use a fresh mock builder for this test
        val freshMockBuilder: MessageCreateParams.Builder =
            mock(MessageCreateParams.Builder::class.java)
        val freshDelegator = StructuredMessageCreateParams.builder<X>().inject(freshMockBuilder)

        delegatorMethod.invoke(freshDelegator, delegatorTestCase.inputValues[0])

        // Verify that outputConfig was called with the derived OutputConfig
        val expectedFormat = outputFormatFromClass(X::class.java)
        val expectedOutputConfig = OutputConfig.builder().format(expectedFormat).build()
        verify(freshMockBuilder, times(1)).outputConfig(expectedOutputConfig)
        verifyNoMoreInteractions(freshMockBuilder)
    }

    @Test
    fun `GA version does not inject beta header`() {
        // Verify that the GA version does NOT add any beta header
        val builder =
            MessageCreateParams.builder()
                .maxTokens(1024)
                .model(Model.CLAUDE_4_SONNET_20250514)
                .addUserMessage("test")
                .outputConfig(X::class.java)
                .build()

        val params = builder.rawParams

        // No beta header should be present
        val headers = params._headers()
        assertThat(headers.names()).doesNotContain("anthropic-beta")
    }
}
