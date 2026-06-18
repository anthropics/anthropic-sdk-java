package com.anthropic.helpers

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonNull
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.messages.*
import com.anthropic.models.messages.Model
import com.anthropic.services.blocking.beta.MessageService
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Supplier
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class BetaToolRunnerTest {

    private val messageService = mock<MessageService>()
    private val initialMessageParams =
        MessageCreateParams.builder()
            .model(Model.CLAUDE_SONNET_4_5)
            .maxTokens(1000)
            .addUserMessage("What is the weather in San Francisco?")
            // NOTE: In tests, identify tools with their snake-case names (e.g., "get_weather").
            .addTool(GetWeather::class.java)
            .addTool(BrokenGetWeather::class.java)
            .build()
    private val requestOptions = RequestOptions.builder().timeout(Duration.ofSeconds(42)).build()

    private val toolRunner =
        BetaToolRunner(
            messageService,
            ToolRunnerCreateParams.builder()
                .initialMessageParams(initialMessageParams)
                .maxIterations(2)
                .build(),
            requestOptions,
        )

    @Test
    fun iterator_whenIteratorAlreadyCalled_throws() {
        toolRunner.iterator()

        val exception = assertThrows<IllegalStateException> { toolRunner.iterator() }

        assertThat(exception).hasMessage("Cannot iterate the same `BetaToolRunner` twice")
    }

    @Test
    fun iterator_whenStreamingIteratorAlreadyCalled_throws() {
        toolRunner.streaming().iterator()

        val exception = assertThrows<IllegalStateException> { toolRunner.iterator() }

        assertThat(exception).hasMessage("Cannot iterate the same `BetaToolRunner` twice")
    }

    @Test
    fun streamingIterator_whenIteratorAlreadyCalled_throws() {
        toolRunner.iterator()

        val exception = assertThrows<IllegalStateException> { toolRunner.streaming().iterator() }

        assertThat(exception).hasMessage("Cannot iterate the same `BetaToolRunner` twice")
    }

    @Test
    fun streamingIterator_whenStreamingIteratorAlreadyCalled_throws() {
        toolRunner.streaming().iterator()

        val exception = assertThrows<IllegalStateException> { toolRunner.streaming().iterator() }

        assertThat(exception).hasMessage("Cannot iterate the same `BetaToolRunner` twice")
    }

    @Test
    fun lastToolResponse_whenNotIterated_returnsEmptyOptional() {
        assertThat(toolRunner.lastToolResponse()).isEmpty
    }

    @Test
    fun iteration_whenNoToolUse_stops() {
        val assistantMessage =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text("The weather is just dandy")
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(assistantMessage)
        assertThat(toolRunner.lastToolResponse()).isEmpty
    }

    @Test
    fun iteration_whenToolUseNameNotFound_respondsWithError() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_the_weather")
                        .input(JsonValue.from(mapOf("location" to "San Francisco")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("Error: Tool 'get_the_weather' not found")
                                .isError(true)
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text("The weather in San Francisco is 404!")
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(assistantMessage1, assistantMessage2)
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenToolUseThrows_respondsWithError() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("broken_get_weather")
                        .input(JsonValue.from(mapOf("location" to "San Francisco")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("Error: BOOM!")
                                .isError(true)
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text("The weather in San Francisco is explosive!")
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(assistantMessage1, assistantMessage2)
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenToolUse_continues() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "San Francisco")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("The weather in San Francisco is foggy and 60°F")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text(
                            "The weather in San Francisco is foggy and 60°F, and you should totally visit!"
                        )
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(assistantMessage1, assistantMessage2)
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenRefusal_stopsWithoutExecutingTools() {
        val refusalMessage =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Refusal City (sync)")))
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.REFUSAL)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(refusalMessage)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(refusalMessage)
        assertThat(GetWeather.executions).doesNotContainKey("Refusal City (sync)")
        verify(messageService, times(1)).create(any<MessageCreateParams>(), any())
    }

    @Test
    fun streamingIteration_whenRefusal_stopsWithoutExecutingTools() {
        val events =
            listOf(
                BetaRawMessageStreamEvent.ofMessageStart(
                    BetaRawMessageStartEvent.builder()
                        .message(
                            betaMessageBuilder()
                                .content(listOf())
                                .stopDetails(JsonMissing.of())
                                .stopReason(JsonMissing.of())
                                .stopSequence(JsonMissing.of())
                                .contextManagement(null)
                                .build()
                        )
                        .build()
                ),
                BetaRawMessageStreamEvent.ofContentBlockStart(
                    BetaRawContentBlockStartEvent.builder()
                        .index(0L)
                        .contentBlock(
                            BetaRawContentBlockStartEvent.ContentBlock.ofToolUse(
                                BetaToolUseBlock.builder()
                                    .id("toolUseId")
                                    .name("get_weather")
                                    .input(JsonNull.of())
                                    .build()
                            )
                        )
                        .build()
                ),
                BetaRawMessageStreamEvent.ofContentBlockDelta(
                    BetaRawContentBlockDeltaEvent.builder()
                        .index(0L)
                        .delta(
                            BetaInputJsonDelta.builder()
                                .partialJson("""{"location":"Refusal City (streaming)"}""")
                                .build()
                        )
                        .build()
                ),
                BetaRawMessageStreamEvent.ofContentBlockStop(
                    BetaRawContentBlockStopEvent.builder().index(0L).build()
                ),
                BetaRawMessageStreamEvent.ofMessageDelta(
                    BetaRawMessageDeltaEvent.builder()
                        .contextManagement(null)
                        .delta(
                            BetaRawMessageDeltaEvent.Delta.builder()
                                .container(null)
                                .stopDetails(null)
                                .stopReason(BetaStopReason.REFUSAL)
                                .stopSequence(null)
                                .build()
                        )
                        .usage(
                            BetaMessageDeltaUsage.builder()
                                .outputTokens(1L)
                                .outputTokensDetails(null)
                                .cacheCreationInputTokens(0L)
                                .cacheReadInputTokens(0L)
                                .inputTokens(1L)
                                .serverToolUse(null)
                                .iterations(null)
                                .build()
                        )
                        .build()
                ),
                BetaRawMessageStreamEvent.ofMessageStop(BetaRawMessageStopEvent.builder().build()),
            )
        whenever(messageService.createStreaming(initialMessageParams, requestOptions))
            .thenReturn(
                object : StreamResponse<BetaRawMessageStreamEvent> {
                    override fun stream(): Stream<BetaRawMessageStreamEvent> = events.stream()

                    override fun close() {}
                }
            )

        var responses = 0
        for (response in toolRunner.streaming()) {
            response.stream().forEach { _ -> }
            responses++
        }

        assertThat(responses).isEqualTo(1)
        assertThat(GetWeather.executions).doesNotContainKey("Refusal City (streaming)")
        verify(messageService, times(1)).createStreaming(any<MessageCreateParams>(), any())
    }

    @Test
    fun iteration_whenFallbackSeam_executesOnlyPostSeamToolUse() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("preSeamToolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Pre-seam City")))
                        .build()
                )
                .addContent(
                    BetaFallbackBlock.builder()
                        .from(BetaFallbackInfo.builder().model(Model.CLAUDE_SONNET_4_5).build())
                        .to(BetaFallbackInfo.builder().model(Model.CLAUDE_HAIKU_4_5).build())
                        .trigger(BetaFallbackRefusalTrigger.builder().category(null).build())
                        .build()
                )
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("postSeamToolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Post-seam City")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("postSeamToolUseId")
                                .content("The weather in Post-seam City is foggy and 60°F")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text("The weather in San Francisco is foggy and 60°F")
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(assistantMessage1, assistantMessage2)
        assertThat(GetWeather.executions).doesNotContainKey("Pre-seam City")
        assertThat(GetWeather.executions).containsEntry("Post-seam City", 1)
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenTooManyIterations_stops() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "San Francisco")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam1 =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("The weather in San Francisco is foggy and 60°F")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text(
                            "The weather in San Francisco is foggy and 60°F, and you should totally visit!"
                        )
                        .build()
                )
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "New York")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam2 =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("The weather in New York is foggy and 60°F")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage3 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text(
                            "The weather in New York is foggy and 60°F, and you should totally visit!"
                        )
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam1)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam1)
                        .addMessage(assistantMessage2)
                        .addMessage(expectedToolResponseMessageParam2)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage3)

        val messages = toolRunner.toList()

        // Does not include `assistantMessage3` due to stopping early.
        verify(messageService, never())
            .create(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(expectedToolResponseMessageParam1)
                    .addMessage(assistantMessage2)
                    .addMessage(expectedToolResponseMessageParam2)
                    .build(),
                requestOptions,
            )
        assertThat(messages).containsExactly(assistantMessage1, assistantMessage2)
    }

    @Test
    fun iteration_whenNextParamsSet_modifiesNextCall() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "San Francisco")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("The weather is whatever I want!")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text(
                            "The weather in San Francisco is whatever you want, and you should totally visit!"
                        )
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)

        val messages =
            toolRunner
                .asSequence()
                .onEachIndexed { index, message ->
                    if (index == 0) {
                        toolRunner.setNextParams(
                            toolRunner
                                .params()
                                .toBuilder()
                                .addMessage(message)
                                .addUserMessageOfBetaContentBlockParams(
                                    listOf(
                                        BetaContentBlockParam.ofToolResult(
                                            BetaToolResultBlockParam.builder()
                                                .toolUseId("toolUseId")
                                                .content("The weather is whatever I want!")
                                                .build()
                                        )
                                    )
                                )
                                .build()
                        )
                    }
                }
                .toList()

        assertThat(messages).containsExactly(assistantMessage1, assistantMessage2)
    }

    @Test
    fun setNextParams_invalidatesLastToolResponse() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "San Francisco")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val expectedToolResponseMessageParam =
            BetaMessageParam.builder()
                .role(BetaMessageParam.Role.USER)
                .contentOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId")
                                .content("The weather in San Francisco is foggy and 60°F")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text("The weather in San Francisco is foggy and 60°F, done!")
                        .build()
                )
                .contextManagement(null)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    initialMessageParams
                        .toBuilder()
                        .addMessage(assistantMessage1)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage2)

        toolRunner.toList()

        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
        toolRunner.setNextParams(toolRunner.params())
        assertThat(toolRunner.lastToolResponse()).isEmpty
    }

    private fun betaMessageBuilder() =
        BetaMessage.builder()
            .id("id")
            .model(Model.CLAUDE_SONNET_4_5)
            .container(null)
            .diagnostics(null)
            .stopDetails(null)
            .stopReason(null)
            .stopSequence(null)
            .usage(betaUsage())

    private fun betaUsage() =
        BetaUsage.builder()
            .cacheCreation(
                BetaCacheCreation.builder()
                    .ephemeral1hInputTokens(0L)
                    .ephemeral5mInputTokens(0L)
                    .build()
            )
            .cacheCreationInputTokens(2051L)
            .cacheReadInputTokens(2051L)
            .inputTokens(2095L)
            .outputTokens(503L)
            .outputTokensDetails(BetaOutputTokensDetails.builder().thinkingTokens(0L).build())
            .serverToolUse(
                BetaServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
            )
            .serviceTier(BetaUsage.ServiceTier.STANDARD)
            .speed(null)
            .inferenceGeo(null)
            .iterations(null)
            .build()
}

@JsonClassDescription("Get the weather in a given location")
private class GetWeather : Supplier<String> {
    @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
    lateinit var location: String

    override fun get(): String {
        executions.merge(location, 1, Int::plus)
        return "The weather in $location is foggy and 60°F"
    }

    companion object {
        /**
         * Execution counts per location so tests can assert a tool call never ran. Tests execute in
         * parallel, so each test that asserts on this must use a location unique to it.
         */
        val executions = ConcurrentHashMap<String, Int>()
    }
}

@JsonClassDescription("Get the weather in a given location")
private class BrokenGetWeather : Supplier<String> {
    @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
    lateinit var location: String

    override fun get(): String = throw UnsupportedOperationException("BOOM!")
}
