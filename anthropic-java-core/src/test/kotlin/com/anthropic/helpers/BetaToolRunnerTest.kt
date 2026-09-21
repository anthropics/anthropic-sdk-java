package com.anthropic.helpers

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonNull
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.NestedClassJavaFixtures
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.jsonMapper
import com.anthropic.core.toolFromClass
import com.anthropic.errors.AnthropicIoException
import com.anthropic.models.beta.messages.*
import com.anthropic.models.messages.Model
import com.anthropic.services.blocking.beta.MessageService
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import io.swagger.v3.oas.annotations.media.Schema
import java.time.Duration
import java.time.OffsetDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Supplier
import java.util.stream.Stream
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
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
            .putAdditionalHeader(STAINLESS_HELPER_HEADER, "BetaToolRunner")
            .build()
    private val requestOptions = RequestOptions.builder().timeout(Duration.ofSeconds(42)).build()
    private val webSearch =
        BetaToolUnion.ofWebSearchTool20250305(
            BetaWebSearchTool20250305.builder().maxUses(3L).build()
        )

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
                .stopReason(BetaStopReason.END_TURN)
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
                .stopReason(BetaStopReason.END_TURN)
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
    fun iteration_whenToolRemoved_respondsWithNotFoundError() {
        // A `tool_use` for a `tool_removal`ed tool must produce the same result as a `tool_use`
        // for a tool that was never declared in `tools`.
        val paramsWithRemoval =
            initialMessageParams
                .toBuilder()
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolRemoval(
                            BetaRequestToolRemovalBlock.builder()
                                .referenceTool("get_weather")
                                .build()
                        )
                    )
                )
                .build()
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(paramsWithRemoval)
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Removed City")))
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
                                .content("Error: Tool 'get_weather' not found")
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
                        .text("The weather in Removed City is 404!")
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(paramsWithRemoval, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    paramsWithRemoval
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
        assertThat(GetWeather.executions).doesNotContainKey("Removed City")
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenToolRemovedThenAdded_executesTool() {
        val paramsWithChanges =
            initialMessageParams
                .toBuilder()
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolRemoval(
                            BetaRequestToolRemovalBlock.builder()
                                .referenceTool("get_weather")
                                .build()
                        )
                    )
                )
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolAddition(
                            BetaRequestToolAdditionBlock.builder()
                                .referenceTool("get_weather")
                                .build()
                        )
                    )
                )
                .build()
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(paramsWithChanges)
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Re-added City")))
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
                                .content("The weather in Re-added City is foggy and 60°F")
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
                        .text("The weather in Re-added City is foggy and 60°F!")
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(paramsWithChanges, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    paramsWithChanges
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
        assertThat(GetWeather.executions).containsEntry("Re-added City", 1)
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun addTool_whenHandlingMessage_sendsDefinitionAfterToolResultsAndRunsTool() {
        val toolRunner = newToolRunner(maxIterations = 3)
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Added Tool City"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Added Tool City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, assistantMessage2, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.addTool(GetTime::class.java)
            }
        }

        val requests = sentRequests(3)
        val requestWithAddition =
            initialMessageParams
                .toBuilder()
                .addMessage(assistantMessage1)
                .addMessage(getWeatherToolResponse("Added Tool City"))
                .addSystemMessageOfBetaContentBlockParams(listOf(toolAddition(GetTime::class.java)))
                .build()
        assertThat(requests[0]).isEqualTo(initialMessageParams)
        assertThat(requests[1]).isEqualTo(requestWithAddition)
        assertThat(requests[2])
            .isEqualTo(
                requestWithAddition
                    .toBuilder()
                    .addMessage(assistantMessage2)
                    .addMessage(toolResponse("12:00 in Added Tool City"))
                    .build()
            )
        assertThat(requests.map { it.tools() }).containsOnly(initialMessageParams.tools())
    }

    @Test
    fun addTool_whenRunnableTool_sendsDefinitionAndRunsToolUntilRemoved() {
        val getTime =
            BetaRunnableTool.of(
                GetTime::class.java,
                { BetaToolResultBlockParam.Content.ofString("noon in ${it.location}") },
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Runnable Tool City"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Removed Runnable Tool City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, assistantMessage2, finalAssistantMessage())
        val toolRunner = newToolRunner(maxIterations = 3)
        val sameNameTool =
            BetaRunnableTool.of(
                GetTime::class.java,
                { BetaToolResultBlockParam.Content.ofString("midnight in ${it.location}") },
            )

        toolRunner.addTool(getTime)
        toolRunner.runHandlingEachMessage { index ->
            when (index) {
                0 -> toolRunner.removeTool(sameNameTool)
                1 -> toolRunner.removeTool(getTime)
            }
        }

        assertThat(sentRequests(3)[2])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolAddition(GetTime::class.java))
                    )
                    .addMessage(assistantMessage1)
                    .addMessage(toolResponse("noon in Runnable Tool City"))
                    .addMessage(assistantMessage2)
                    .addMessage(toolNotFoundResponse("get_time"))
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_time")))
                    .build()
            )
    }

    @Test
    fun removeTool_whenGivenRunnableTool_removesToolDeclaredUpFrontOrJustAdded() {
        val getTime =
            BetaRunnableTool.of(
                GetTime::class.java,
                { BetaToolResultBlockParam.Content.ofString("noon in ${it.location}") },
            )
        val getDateDefinition =
            BetaTool.builder()
                .name("get_date")
                .inputSchema(BetaTool.InputSchema.builder().build())
                .build()
        val getDate =
            BetaRunnableTool.of(getDateDefinition) { BetaToolResultBlockParam.Content.ofString(it) }
        val params = initialMessageParams.toBuilder().addTool(getTime).build()
        val toolRunner = newToolRunner(params)
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Own Tool City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.removeTool(getTime)
                toolRunner.addTool(getDate)
                toolRunner.removeTool(getDate)
            }
        }

        assertThat(sentRequests(2)[1])
            .isEqualTo(
                params
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(toolNotFoundResponse("get_time"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(
                            toolRemoval("get_time"),
                            toolAddition(BetaToolUnion.ofBetaTool(getDateDefinition)),
                            toolRemoval("get_date"),
                        )
                    )
                    .build()
            )
    }

    @Test
    fun addTool_whenLocalValidationOff_sendsDefinitionThatLocalValidationRejects() {
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(finalAssistantMessage())

        assertThrows<IllegalArgumentException> {
            toolRunner.addTool(UnsupportedSchemaTool::class.java)
        }
        toolRunner.addTool(UnsupportedSchemaTool::class.java, JsonSchemaLocalValidation.NO)
        toolRunner.toList()

        val definition =
            toolFromClass(UnsupportedSchemaTool::class.java, JsonSchemaLocalValidation.NO)
        assertThat(sentRequests(1)[0])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolAddition(BetaToolUnion.ofBetaTool(definition)))
                    )
                    .build()
            )
    }

    @Test
    fun addTool_whenMcpTool_sendsDefinitionAndRunsTool() {
        val echo =
            McpBetaTool.builder()
                .name("echo")
                .definition(
                    BetaTool.builder()
                        .name("echo")
                        .inputSchema(BetaTool.InputSchema.builder().build())
                        .build()
                )
                .runner { BetaToolResultBlockParam.Content.ofString("echo: $it") }
                .build()
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(toolUse("echo", "MCP Tool City"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(toolUse("echo", "Removed MCP Tool City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, assistantMessage2, finalAssistantMessage())
        val toolRunner = newToolRunner(maxIterations = 3)

        toolRunner.addTool(echo)
        toolRunner.runHandlingEachMessage { index ->
            if (index == 1) {
                toolRunner.removeTool(echo)
            }
        }

        val requests = sentRequests(3)
        val requestWithAddition =
            initialMessageParams
                .toBuilder()
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(toolAddition(BetaToolUnion.ofBetaTool(echo.definition)))
                )
                .build()
        assertThat(requests[0]).isEqualTo(requestWithAddition)
        assertThat(requests[2])
            .isEqualTo(
                requestWithAddition
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(toolResponse("""echo: {"location":"MCP Tool City"}"""))
                    .addMessage(assistantMessage2)
                    .addMessage(toolNotFoundResponse("echo"))
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("echo")))
                    .build()
            )
    }

    @Test
    fun removeTool_whenHandlingMessage_refusesThatMessagesCallAndSendsRemoval() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Withdrawn City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.removeTool(GetWeather::class.java)
            }
        }

        assertThat(GetWeather.executions).doesNotContainKey("Withdrawn City")
        val requests = sentRequests(2)
        assertThat(requests[1])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_weather")))
                    .build()
            )
        assertThat(requests.map { it.tools() }).containsOnly(initialMessageParams.tools())
    }

    @Test
    fun removeTool_whenGivenClassOfToolAddedEarlier_removesThatTool() {
        val toolRunner = newToolRunner(maxIterations = 3)
        val assistantMessage1 = toolUseMessage("Added Then Removed City")
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Added Then Removed City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, assistantMessage2, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            when (index) {
                0 -> toolRunner.addTool(GetTime::class.java)
                1 -> toolRunner.removeTool(GetTime::class.java)
            }
        }

        assertThat(sentRequests(3)[2])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(getWeatherToolResponse("Added Then Removed City"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolAddition(GetTime::class.java))
                    )
                    .addMessage(assistantMessage2)
                    .addMessage(toolNotFoundResponse("get_time"))
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_time")))
                    .build()
            )
    }

    @Test
    fun removeTool_whenGivenAnotherClassOfSameToolName_leavesToolAlone() {
        val assistantMessage1 = toolUseMessage("Same Name City")
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.removeTool(SunnyGetWeather::class.java)
        toolRunner.toList()

        assertThat(GetWeather.executions).containsEntry("Same Name City", 1)
        assertThat(sentRequests(2)[0]).isEqualTo(initialMessageParams)
    }

    @Test
    fun removeTool_whenClassHasJsonTypeName_findsToolByItsClass() {
        val params = initialMessageParams.toBuilder().addTool(SunnyGetTime::class.java).build()
        val toolRunner = newToolRunner(params)
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Type Name City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.removeTool(SunnyGetTime::class.java)
        toolRunner.toList()

        assertThat(sentRequests(2)[1])
            .isEqualTo(
                params
                    .toBuilder()
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_time")))
                    .addMessage(assistantMessage1)
                    .addMessage(toolNotFoundResponse("get_time"))
                    .build()
            )
    }

    @Test
    fun removeTool_whenRemovalLeavesHistory_keepsToolRemoved() {
        val toolRunner = newToolRunner(maxIterations = 4)
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Trimmed History City 1"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Trimmed History City 2"))
                .contextManagement(null)
                .build()
        val assistantMessage3 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Trimmed History City 3"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                assistantMessage1,
                assistantMessage2,
                assistantMessage3,
                finalAssistantMessage(),
            )

        toolRunner.runHandlingEachMessage { index ->
            when (index) {
                0 -> toolRunner.removeTool("get_weather")
                // Drops the `"system"` message that carried the removal.
                1 -> toolRunner.setNextParams(initialMessageParams)
            }
        }

        assertThat(GetWeather.executions.keys).noneMatch { it.startsWith("Trimmed History City") }
        val requests = sentRequests(4)
        assertThat(requests[2]).isEqualTo(initialMessageParams)
        assertThat(requests[3])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage3)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .build()
            )
    }

    @Test
    fun addTool_whenToolWasRemoved_runsTheNewToolInItsPlace() {
        val toolRunner = newToolRunner(maxIterations = 4)
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Removed Then Added City 1"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Removed Then Added City 2"))
                .contextManagement(null)
                .build()
        val assistantMessage3 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Removed Then Added City 3"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                assistantMessage1,
                assistantMessage2,
                assistantMessage3,
                finalAssistantMessage(),
            )

        toolRunner.runHandlingEachMessage { index ->
            when (index) {
                0 -> toolRunner.removeTool("get_weather")
                1 -> toolRunner.addTool(SunnyGetWeather::class.java)
            }
        }

        assertThat(sentRequests(4)[3])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_weather")))
                    .addMessage(assistantMessage2)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolAddition(SunnyGetWeather::class.java))
                    )
                    .addMessage(assistantMessage3)
                    .addMessage(toolResponse("The weather in Removed Then Added City 3 is sunny"))
                    .build()
            )
    }

    @Test
    fun toolChanges_whenMadeWhileHandlingOneMessage_areSentTogetherInCallOrder() {
        val toolRunner = newToolRunner(maxIterations = 3)
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Call Order City 1"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(toolUse("get_time", "Call Order City 2", id = "toolUseId1"))
                .addContent(toolUse("get_weather", "Call Order City 2", id = "toolUseId2"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, assistantMessage2, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.addTool(GetTime::class.java)
                toolRunner.removeTool(GetTime::class.java)
                toolRunner.addTool(webSearch)
                toolRunner.removeTool("get_weather")
                toolRunner.addTool(GetWeather::class.java)
            }
        }

        assertThat(GetWeather.executions).doesNotContainKey("Call Order City 1")
        val requests = sentRequests(3)
        val requestWithChanges =
            initialMessageParams
                .toBuilder()
                .addMessage(assistantMessage1)
                .addMessage(toolNotFoundResponse("get_weather"))
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        toolAddition(GetTime::class.java),
                        toolRemoval("get_time"),
                        toolAddition(webSearch),
                        toolRemoval("get_weather"),
                        toolAddition(GetWeather::class.java),
                    )
                )
                .build()
        assertThat(requests[1]).isEqualTo(requestWithChanges)
        assertThat(requests[2])
            .isEqualTo(
                requestWithChanges
                    .toBuilder()
                    .addMessage(assistantMessage2)
                    .addUserMessageOfBetaContentBlockParams(
                        listOf(
                            toolNotFoundResult("get_time", id = "toolUseId1"),
                            toolResult(
                                "The weather in Call Order City 2 is foggy and 60°F",
                                id = "toolUseId2",
                            ),
                        )
                    )
                    .build()
            )
    }

    @Test
    fun addTool_whenRawDefinition_sendsItAsGivenAndStopsRunningToolOfThatName() {
        val toolRunner = newToolRunner(maxIterations = 3)
        val mcpToolset = BetaToolUnion.ofMcpToolset("docs")
        val rawGetWeather = BetaToolUnion.ofBetaTool(toolFromClass(SunnyGetWeather::class.java))
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Raw Definition City 1"))
                .contextManagement(null)
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Raw Definition City 2"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, assistantMessage2, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.addTool(mcpToolset)
                toolRunner.addTool(rawGetWeather)
            }
        }

        assertThat(GetWeather.executions).containsEntry("Raw Definition City 1", 1)
        assertThat(GetWeather.executions).doesNotContainKey("Raw Definition City 2")
        assertThat(sentRequests(3)[2])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(getWeatherToolResponse("Raw Definition City 1"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolAddition(mcpToolset), toolAddition(rawGetWeather))
                    )
                    .addMessage(assistantMessage2)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .build()
            )
    }

    @Test
    fun toolChanges_whenTurnPaused_areSentOneRequestLater() {
        val toolRunner = newToolRunner(maxIterations = 3)
        val pausedMessage = pausedServerToolUseMessage()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Paused Turn City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(pausedMessage, assistantMessage2, finalAssistantMessage())

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.removeTool("get_weather")
                toolRunner.addTool(GetTime::class.java)
            }
        }

        assertThat(GetWeather.executions).doesNotContainKey("Paused Turn City")
        val requests = sentRequests(3)
        val resumedRequest = initialMessageParams.toBuilder().addMessage(pausedMessage).build()
        assertThat(requests[1]).isEqualTo(resumedRequest)
        assertThat(requests[2])
            .isEqualTo(
                resumedRequest
                    .toBuilder()
                    .addMessage(assistantMessage2)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolRemoval("get_weather"), toolAddition(GetTime::class.java))
                    )
                    .build()
            )
    }

    @Test
    fun toolChanges_aroundCompaction_goWithTheCompactionRequestOrFollowItsResponse() {
        val toolUseMessage = toolUseMessage("Compacting City")
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(toolUseMessage, compactionResponse, finalAssistantMessage())
        val toolRunner = newToolRunner(maxIterations = 2)

        toolRunner.runHandlingEachMessage { index ->
            when (index) {
                0 -> {
                    toolRunner.addTool(GetTime::class.java)
                    toolRunner.compactBeforeNextTurn()
                }
                1 -> toolRunner.removeTool("get_weather")
            }
        }

        val requests = sentRequests(3)
        assertThat(requests[1])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(toolUseMessage)
                    .addMessage(getWeatherToolResponse("Compacting City"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolAddition(GetTime::class.java))
                    )
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
        assertThat(requests[2])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .messages(listOf(compactionResponse.toParam()))
                    .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_weather")))
                    .build()
            )
    }

    @Test
    fun compaction_whenHistoryRemovedToolAndResponseHasNoToolChanges_keepsToolRemoved() {
        val params =
            initialMessageParams
                .toBuilder()
                .addSystemMessageOfBetaContentBlockParams(listOf(toolRemoval("get_weather")))
                .build()
        val toolRunner = newToolRunner(params, maxIterations = 2)
        val compactionResponse = compactionResponse()
        val toolUseMessage = toolUseMessage("Removed Before Compaction City")
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(compactionResponse, toolUseMessage, finalAssistantMessage())

        toolRunner.compactBeforeNextTurn()
        toolRunner.toList()

        assertThat(GetWeather.executions).doesNotContainKey("Removed Before Compaction City")
        assertThat(sentRequests(3)[2])
            .isEqualTo(
                params
                    .toBuilder()
                    .messages(listOf(compactionResponse.toParam()))
                    .addMessage(toolUseMessage)
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .build()
            )
    }

    @Test
    fun toolChanges_whenRunEndsBeforeNextRequest_areNeverSent() {
        val finalMessage = finalAssistantMessage()
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(finalMessage, compactionResponse)

        toolRunner.runHandlingEachMessage { index ->
            if (index == 0) {
                toolRunner.addTool(GetTime::class.java)
                toolRunner.removeTool("get_weather")
                // Not even the compaction request that follows the final turn carries them.
                toolRunner.compactBeforeNextTurn()
            }
        }

        val requests = sentRequests(2)
        assertThat(requests[0]).isEqualTo(initialMessageParams)
        assertThat(requests[1])
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(finalMessage)
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
    }

    @Test
    fun streamingToolChanges_whenHandlingResponse_areSentAfterToolResults() {
        val toolUseEvents = getWeatherToolUseStreamEvents("Streamed Changes City")
        val finalEvents =
            listOf(
                BetaRawMessageStreamEvent.ofMessageStart(
                    BetaRawMessageStartEvent.builder()
                        .message(finalAssistantMessage().toBuilder().content(listOf()).build())
                        .build()
                ),
                BetaRawMessageStreamEvent.ofMessageStop(BetaRawMessageStopEvent.builder().build()),
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(streamResponseOf(toolUseEvents), streamResponseOf(finalEvents))

        toolRunner.streaming().forEachIndexed { index, response ->
            response.stream().forEach { _ -> }
            if (index == 0) {
                toolRunner.removeTool("get_weather")
                toolRunner.addTool(GetTime::class.java)
            }
        }

        assertThat(GetWeather.executions).doesNotContainKey("Streamed Changes City")
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).createStreaming(requests.capture(), any())
        val accumulator = BetaMessageAccumulator.create()
        toolUseEvents.forEach(accumulator::accumulate)
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(accumulator.message())
                    .addMessage(toolNotFoundResponse("get_weather"))
                    .addSystemMessageOfBetaContentBlockParams(
                        listOf(toolRemoval("get_weather"), toolAddition(GetTime::class.java))
                    )
                    .build()
            )
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
                .stopReason(BetaStopReason.END_TURN)
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
                .stopReason(BetaStopReason.END_TURN)
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
    fun iteration_whenRunnableToolUse_runsFunctionWithParsedInput() {
        val locations = mutableListOf<String>()
        val messageParams =
            MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_5)
                .maxTokens(1000)
                .addUserMessage("What is the weather in San Francisco?")
                .addTool(
                    BetaRunnableTool.of(
                        NestedClassJavaFixtures.GetWeather::class.java,
                        {
                            locations.add(it.location)
                            BetaToolResultBlockParam.Content.ofString("Foggy and 60°F")
                        },
                    )
                )
                .putAdditionalHeader(STAINLESS_HELPER_HEADER, "BetaToolRunner")
                .build()
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(messageParams)
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
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
                                .content("Foggy and 60°F")
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
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(messageParams, requestOptions)).thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    messageParams
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
        assertThat(locations).containsExactly("San Francisco")
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenRunnableToolFromDefinitionUse_runsFunctionWithJsonInput() {
        val inputs = mutableListOf<String>()
        val definition =
            BetaTool.builder()
                .name("get_weather")
                .inputSchema(BetaTool.InputSchema.builder().build())
                .build()
        val messageParams =
            MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_4_5)
                .maxTokens(1000)
                .addUserMessage("What is the weather in San Francisco?")
                .addTool(
                    BetaRunnableTool.of(definition) {
                        inputs.add(it)
                        BetaToolResultBlockParam.Content.ofString("Foggy and 60°F")
                    }
                )
                .putAdditionalHeader(STAINLESS_HELPER_HEADER, "BetaToolRunner")
                .build()
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(messageParams)
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
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
                                .content("Foggy and 60°F")
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
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(messageParams, requestOptions)).thenReturn(assistantMessage1)
        whenever(
                messageService.create(
                    messageParams
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
        assertThat(inputs).containsExactly("""{"location":"San Francisco"}""")
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
                                .fallbackCredit(null)
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
    fun iteration_whenResponseHasContainer_forwardsItsIdOnNextRequest() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Container City"))
                .container(betaContainer("container-id"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.firstValue.container()).isEmpty
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .container("container-id")
                    .addMessage(assistantMessage1)
                    .addMessage(getWeatherToolResponse("Container City"))
                    .build()
            )
    }

    @Test
    fun iteration_whenResponseHasNoContainer_sendsNoneOnNextRequest() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Containerless City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue._container()).isEqualTo(JsonMissing.of())
    }

    @Test
    fun iteration_whenContainerIdPinned_keepsPinnedContainer() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(
                        initialMessageParams.toBuilder().container("pinned-container-id").build()
                    )
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Pinned Container City"))
                .container(betaContainer("container-id"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue.container())
            .hasValue(MessageCreateParams.Container.ofString("pinned-container-id"))
    }

    @Test
    fun iteration_whenPinnedContainerParamsHaveNoId_fillsInResponseContainerId() {
        val skill =
            BetaSkillParams.builder()
                .skillId("pptx")
                .type(BetaSkillParams.Type.ANTHROPIC)
                .version("latest")
                .build()
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(
                        initialMessageParams
                            .toBuilder()
                            .container(BetaContainerParams.builder().addSkill(skill).build())
                            .build()
                    )
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Skills Container City"))
                .container(betaContainer("container-id"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue.container())
            .hasValue(
                MessageCreateParams.Container.ofBetaContainerParams(
                    BetaContainerParams.builder().addSkill(skill).id("container-id").build()
                )
            )
    }

    @Test
    fun streamingIteration_whenResponseHasContainer_forwardsItsIdOnNextRequest() {
        val toolUseEvents =
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
                                .partialJson("""{"location":"Streamed Container City"}""")
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
                                .container(betaContainer("streamed-container-id"))
                                .stopDetails(null)
                                .stopReason(BetaStopReason.TOOL_USE)
                                .stopSequence(null)
                                .build()
                        )
                        .usage(
                            BetaMessageDeltaUsage.builder()
                                .fallbackCredit(null)
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
        val finalEvents =
            listOf(
                BetaRawMessageStreamEvent.ofMessageStart(
                    BetaRawMessageStartEvent.builder()
                        .message(finalAssistantMessage().toBuilder().content(listOf()).build())
                        .build()
                ),
                BetaRawMessageStreamEvent.ofMessageStop(BetaRawMessageStopEvent.builder().build()),
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(streamResponseOf(toolUseEvents), streamResponseOf(finalEvents))

        var responses = 0
        for (response in toolRunner.streaming()) {
            response.stream().forEach { _ -> }
            responses++
        }

        assertThat(responses).isEqualTo(2)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).createStreaming(requests.capture(), any())
        assertThat(requests.firstValue.container()).isEmpty
        assertThat(requests.secondValue.container())
            .hasValue(MessageCreateParams.Container.ofString("streamed-container-id"))
    }

    @Test
    fun iteration_whenNoHelperHeader_tagsEveryRequestOnce() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(
                        initialMessageParams
                            .toBuilder()
                            .removeAdditionalHeaders(STAINLESS_HELPER_HEADER)
                            .build()
                    )
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Untagged City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(
                requests.allValues.map { it._additionalHeaders().values(STAINLESS_HELPER_HEADER) }
            )
            .containsExactly(listOf("BetaToolRunner"), listOf("BetaToolRunner"))
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(assistantMessage1)
                    .addMessage(getWeatherToolResponse("Untagged City"))
                    .build()
            )
    }

    @Test
    fun iteration_whenHelperHeaderAlreadySet_appendsRunnerTagOnce() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(
                        initialMessageParams
                            .toBuilder()
                            .replaceAdditionalHeaders(STAINLESS_HELPER_HEADER, "something-else")
                            .build()
                    )
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Pre-tagged City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(
                requests.allValues.map { it._additionalHeaders().values(STAINLESS_HELPER_HEADER) }
            )
            .containsExactly(
                listOf("something-else, BetaToolRunner"),
                listOf("something-else, BetaToolRunner"),
            )
    }

    @Test
    fun iteration_whenNextParamsUntagged_tagsNextRequest() {
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Untagged Next Params City"))
                .contextManagement(null)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        toolRunner
            .asSequence()
            .onEachIndexed { index, message ->
                if (index == 0) {
                    toolRunner.setNextParams(
                        toolRunner
                            .params()
                            .toBuilder()
                            .removeAdditionalHeaders(STAINLESS_HELPER_HEADER)
                            .addMessage(message)
                            .addMessage(getWeatherToolResponse("Untagged Next Params City"))
                            .build()
                    )
                }
            }
            .toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(
                requests.allValues.map { it._additionalHeaders().values(STAINLESS_HELPER_HEADER) }
            )
            .containsExactly(listOf("BetaToolRunner"), listOf("BetaToolRunner"))
        assertThat(GetWeather.executions).doesNotContainKey("Untagged Next Params City")
    }

    @Test
    fun streamingIteration_whenNoHelperHeader_tagsEveryRequestOnce() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(
                        initialMessageParams
                            .toBuilder()
                            .removeAdditionalHeaders(STAINLESS_HELPER_HEADER)
                            .build()
                    )
                    .maxIterations(2)
                    .build(),
                requestOptions,
            )
        val finalEvents =
            listOf(
                BetaRawMessageStreamEvent.ofMessageStart(
                    BetaRawMessageStartEvent.builder()
                        .message(finalAssistantMessage().toBuilder().content(listOf()).build())
                        .build()
                ),
                BetaRawMessageStreamEvent.ofMessageStop(BetaRawMessageStopEvent.builder().build()),
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(
                streamResponseOf(getWeatherToolUseStreamEvents("Untagged Streaming City")),
                streamResponseOf(finalEvents),
            )

        var responses = 0
        for (response in toolRunner.streaming()) {
            response.stream().forEach { _ -> }
            responses++
        }

        assertThat(responses).isEqualTo(2)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).createStreaming(requests.capture(), any())
        assertThat(
                requests.allValues.map { it._additionalHeaders().values(STAINLESS_HELPER_HEADER) }
            )
            .containsExactly(listOf("BetaToolRunner"), listOf("BetaToolRunner"))
    }

    @Test
    fun iteration_whenPauseTurn_resendsPausedTurn() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(initialMessageParams)
                    .maxIterations(4)
                    .build(),
                requestOptions,
            )
        val pausedMessage = pausedServerToolUseMessage()
        val resumedMessage =
            betaMessageBuilder()
                .addContent(
                    BetaTextBlock.builder()
                        .citations(null)
                        .text("The weather in San Francisco is foggy and 60°F")
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(pausedMessage)
        whenever(
                messageService.create(
                    initialMessageParams.toBuilder().addMessage(pausedMessage).build(),
                    requestOptions,
                )
            )
            .thenReturn(resumedMessage)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(pausedMessage, resumedMessage)
        assertThat(toolRunner.params().messages().last()).isEqualTo(pausedMessage.toParam())
        assertThat(toolRunner.lastToolResponse()).isEmpty
        verify(messageService, times(2)).create(any<MessageCreateParams>(), any())
    }

    @Test
    fun streamingIteration_whenPauseTurn_resendsPausedTurn() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(initialMessageParams)
                    .maxIterations(4)
                    .build(),
                requestOptions,
            )
        val pausedEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofServerToolUse(
                    BetaServerToolUseBlock.builder()
                        .id("srvtoolu_1")
                        .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                        .input(JsonNull.of())
                        .build()
                ),
                BetaRawContentBlockDelta.ofInputJson(
                    BetaInputJsonDelta.builder()
                        .partialJson("""{"query":"weather in San Francisco"}""")
                        .build()
                ),
                BetaStopReason.PAUSE_TURN,
            )
        val resumedEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofText(
                    BetaTextBlock.builder().citations(null).text("").build()
                ),
                BetaRawContentBlockDelta.ofText(
                    BetaTextDelta.builder().text("The weather in San Francisco is foggy").build()
                ),
                BetaStopReason.END_TURN,
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(streamResponseOf(pausedEvents), streamResponseOf(resumedEvents))

        val stopReasons = mutableListOf<BetaStopReason>()
        for (response in toolRunner.streaming()) {
            val accumulator = BetaMessageAccumulator.create()
            response.stream().forEach(accumulator::accumulate)
            stopReasons.add(accumulator.message().stopReason().get())
        }

        assertThat(stopReasons).containsExactly(BetaStopReason.PAUSE_TURN, BetaStopReason.END_TURN)
        assertThat(toolRunner.params().messages()).hasSize(2)
        val resentTurn = toolRunner.params().messages().last()
        assertThat(resentTurn.role()).isEqualTo(BetaMessageParam.Role.ASSISTANT)
        assertThat(resentTurn.content().betaContentBlockParams().get().single().isServerToolUse())
            .isTrue()
        assertThat(toolRunner.lastToolResponse()).isEmpty
        verify(messageService, times(2)).createStreaming(any<MessageCreateParams>(), any())
    }

    @Test
    fun iteration_whenPauseTurnRepeats_stopsAtMaxIterations() {
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(pausedServerToolUseMessage())

        val messages = toolRunner.toList()

        assertThat(messages).hasSize(2)
        assertThat(messages.last().stopReason()).hasValue(BetaStopReason.PAUSE_TURN)
        verify(messageService, times(2)).create(any<MessageCreateParams>(), any())
    }

    @Test
    fun iteration_whenCompaction_resendsCompactedTurn() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(initialMessageParams)
                    .maxIterations(4)
                    .build(),
                requestOptions,
            )
        val compactedMessage =
            betaMessageBuilder()
                .addContent(
                    BetaCompactionBlock.builder()
                        .content("The user asked about the weather in San Francisco.")
                        .encryptedContent(null)
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.COMPACTION)
                .build()
        val resumedMessage = finalAssistantMessage()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(compactedMessage)
        whenever(
                messageService.create(
                    initialMessageParams.toBuilder().addMessage(compactedMessage).build(),
                    requestOptions,
                )
            )
            .thenReturn(resumedMessage)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(compactedMessage, resumedMessage)
        assertThat(toolRunner.params().messages().last()).isEqualTo(compactedMessage.toParam())
        assertThat(toolRunner.lastToolResponse()).isEmpty
        verify(messageService, times(2)).create(any<MessageCreateParams>(), any())
    }

    @Test
    fun streamingIteration_whenCompaction_resendsCompactedTurn() {
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(initialMessageParams)
                    .maxIterations(4)
                    .build(),
                requestOptions,
            )
        val compactedEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofCompaction(
                    BetaCompactionBlock.builder().content(null).encryptedContent(null).build()
                ),
                BetaRawContentBlockDelta.ofCompaction(
                    BetaCompactionContentBlockDelta.builder()
                        .content("The user asked about the weather in San Francisco.")
                        .encryptedContent(null)
                        .build()
                ),
                BetaStopReason.COMPACTION,
            )
        val resumedEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofText(
                    BetaTextBlock.builder().citations(null).text("").build()
                ),
                BetaRawContentBlockDelta.ofText(
                    BetaTextDelta.builder().text("The weather in San Francisco is foggy").build()
                ),
                BetaStopReason.END_TURN,
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(streamResponseOf(compactedEvents), streamResponseOf(resumedEvents))

        val stopReasons = mutableListOf<BetaStopReason>()
        for (response in toolRunner.streaming()) {
            val accumulator = BetaMessageAccumulator.create()
            response.stream().forEach(accumulator::accumulate)
            stopReasons.add(accumulator.message().stopReason().get())
        }

        assertThat(stopReasons).containsExactly(BetaStopReason.COMPACTION, BetaStopReason.END_TURN)
        assertThat(toolRunner.params().messages()).hasSize(2)
        val resentTurn = toolRunner.params().messages().last()
        assertThat(resentTurn.role()).isEqualTo(BetaMessageParam.Role.ASSISTANT)
        assertThat(resentTurn.content().betaContentBlockParams().get().single().isCompaction())
            .isTrue()
        assertThat(toolRunner.lastToolResponse()).isEmpty
        verify(messageService, times(2)).createStreaming(any<MessageCreateParams>(), any())
    }

    @Test
    fun iteration_whenMaxTokensCutsOffToolUse_stopsWithoutExecutingTools() {
        val truncatedMessage =
            betaMessageBuilder()
                .addContent(getWeatherToolUse("Truncated City"))
                .contextManagement(null)
                .stopReason(BetaStopReason.MAX_TOKENS)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(truncatedMessage)

        val messages = toolRunner.toList()

        assertThat(messages).containsExactly(truncatedMessage)
        assertThat(toolRunner.lastToolResponse()).isEmpty
        assertThat(GetWeather.executions).doesNotContainKey("Truncated City")
        verify(messageService, times(1)).create(any<MessageCreateParams>(), any())
    }

    /** What the loop must do with a turn that calls a client tool, for each stop reason. */
    enum class StopReasonTestCase(
        val stopReason: BetaStopReason?,
        val requests: Int,
        val runsTool: Boolean,
    ) {
        TOOL_USE(BetaStopReason.TOOL_USE, requests = 2, runsTool = true),
        PAUSE_TURN(BetaStopReason.PAUSE_TURN, requests = 2, runsTool = false),
        COMPACTION(BetaStopReason.COMPACTION, requests = 2, runsTool = false),
        END_TURN(BetaStopReason.END_TURN, requests = 1, runsTool = false),
        MAX_TOKENS(BetaStopReason.MAX_TOKENS, requests = 1, runsTool = false),
        STOP_SEQUENCE(BetaStopReason.STOP_SEQUENCE, requests = 1, runsTool = false),
        REFUSAL(BetaStopReason.REFUSAL, requests = 1, runsTool = false),
        MODEL_CONTEXT_WINDOW_EXCEEDED(
            BetaStopReason.MODEL_CONTEXT_WINDOW_EXCEEDED,
            requests = 1,
            runsTool = false,
        ),
        UNKNOWN(BetaStopReason.of("a_newer_stop_reason"), requests = 1, runsTool = false),
        ABSENT(null, requests = 1, runsTool = false),
    }

    @Test
    fun stopReasonTestCases_coverEveryStopReasonValue() {
        assertThat(StopReasonTestCase.values().mapNotNull { it.stopReason?.value() })
            .containsAll(BetaStopReason.Value.values().toList())
    }

    @ParameterizedTest
    @EnumSource
    fun iteration_followsStopReason(testCase: StopReasonTestCase) {
        val location = "Stop Reason City (${testCase.name})"
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(getWeatherToolUse(location))
                .contextManagement(null)
                .stopReason(testCase.stopReason)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(assistantMessage1, finalAssistantMessage())

        val messages = toolRunner.toList()

        assertThat(messages).hasSize(testCase.requests)
        verify(messageService, times(testCase.requests)).create(any<MessageCreateParams>(), any())
        assertThat(GetWeather.executions.containsKey(location)).isEqualTo(testCase.runsTool)
        if (testCase.requests == 2) {
            // The turn went back either answered by a tool_result turn or unchanged.
            assertThat(toolRunner.params().messages().last())
                .isEqualTo(
                    if (testCase.runsTool) getWeatherToolResponse(location)
                    else assistantMessage1.toParam()
                )
        }
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
                .stopReason(BetaStopReason.END_TURN)
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
    fun compactBeforeNextTurn_whenToolUse_compactsAfterToolResults() {
        val contextManagement =
            BetaContextManagementConfig.builder()
                .addEdit(BetaClearToolUses20250919Edit.builder().build())
                .build()
        val params =
            initialMessageParams
                .toBuilder()
                .addBeta("compact-2026-09-04")
                .contextManagement(contextManagement)
                .build()
        // The compaction request is not a model turn, so both real turns still fit.
        val toolRunner = newToolRunner(params, maxIterations = 2)
        val compaction = BetaCompactionConfig.builder().instructions("Keep the city.").build()
        val toolUseMessage = toolUseMessage("Compacted History City")
        val compactionResponse = compactionResponse()
        val finalMessage = finalAssistantMessage()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(toolUseMessage, compactionResponse, finalMessage)

        val messages = mutableListOf<BetaMessage>()
        for (message in toolRunner) {
            messages.add(message)
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn(compaction)
            }
        }

        assertThat(messages).containsExactly(toolUseMessage, compactionResponse, finalMessage)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        assertThat(requests.firstValue).isEqualTo(params)
        assertThat(requests.secondValue)
            .isEqualTo(
                params
                    .toBuilder()
                    .addMessage(toolUseMessage)
                    .addMessage(getWeatherToolResponse("Compacted History City"))
                    .compaction(compaction)
                    .contextManagement(JsonMissing.of())
                    .build()
            )
        // The beta is the caller's to pass, and `contextManagement` is back.
        assertThat(requests.thirdValue)
            .isEqualTo(params.toBuilder().messages(listOf(compactionResponse.toParam())).build())
    }

    @Test
    fun compactBeforeNextTurn_whenCalledBeforeIterating_compactsFirst() {
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(compactionResponse, finalAssistantMessage())

        toolRunner.compactBeforeNextTurn()
        val messages = toolRunner.toList()

        assertThat(messages.map { it.stopReason().get() })
            .containsExactly(BetaStopReason.COMPACTION, BetaStopReason.END_TURN)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.firstValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
        assertThat(requests.secondValue.messages()).containsExactly(compactionResponse.toParam())
    }

    @Test
    fun compactBeforeNextTurn_whenCalledAgain_replacesPendingCompaction() {
        val config = BetaCompactionConfig.builder().instructions("Keep the units.").build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Twice Queued City"),
                compactionResponse(),
                finalAssistantMessage(),
            )

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn(
                    BetaCompactionConfig.builder().instructions("Keep the city.").build()
                )
                toolRunner.compactBeforeNextTurn(config)
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        assertThat(requests.allValues.map { it.compaction().getOrNull() })
            .containsExactly(null, config, null)
    }

    @Test
    fun compactBeforeNextTurn_sendsConfigAsGiven() {
        // Nothing is checked or filled in: empty instructions and options newer than this SDK are
        // the API's business.
        val config =
            BetaCompactionConfig.builder()
                .instructions("")
                .putAdditionalProperty("some_future_option", JsonValue.from(1))
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(compactionResponse(), finalAssistantMessage())

        toolRunner.compactBeforeNextTurn(config)
        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.firstValue.compaction().get()).isSameAs(config)
    }

    @ParameterizedTest
    @ValueSource(strings = ["any", "tool", "auto"])
    fun compactBeforeNextTurn_leavesReplyOnlyParamsOffCompactionRequest(toolChoiceType: String) {
        val toolChoice =
            when (toolChoiceType) {
                "any" -> BetaToolChoice.ofAny(BetaToolChoiceAny.builder().build())
                "tool" ->
                    BetaToolChoice.ofTool(BetaToolChoiceTool.builder().name("get_weather").build())
                else -> BetaToolChoice.ofAuto(BetaToolChoiceAuto.builder().build())
            }
        val format =
            BetaJsonOutputFormat.builder()
                .schema(
                    BetaJsonOutputFormat.Schema.builder()
                        .putAdditionalProperty("type", JsonValue.from("object"))
                        .build()
                )
                .build()
        val effortOnly = BetaOutputConfig.builder().effort(BetaOutputConfig.Effort.LOW).build()
        val effortAndFormat = effortOnly.toBuilder().format(format).build()
        fun fallback(outputConfig: BetaOutputConfig) =
            BetaFallbackParam.builder()
                .model(Model.CLAUDE_HAIKU_4_5)
                .maxTokens(512L)
                .outputConfig(outputConfig)
                .build()
        val params =
            initialMessageParams
                .toBuilder()
                .addBeta("compact-2026-09-04")
                .system("Answer briefly.")
                .addStopSequence("STOP")
                .toolChoice(toolChoice)
                .outputConfig(effortAndFormat)
                .outputFormat(format)
                .fallbacksOfFallbackParams(listOf(fallback(effortAndFormat)))
                .build()
        val toolRunner = newToolRunner(params)
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(compactionResponse, finalAssistantMessage())

        toolRunner.compactBeforeNextTurn()
        toolRunner.toList()

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        val compactionRequest =
            params
                .toBuilder()
                .compaction(BetaCompactionConfig.builder().build())
                .stopSequences(JsonMissing.of())
                .outputConfig(effortOnly)
                .outputFormat(JsonMissing.of())
                .fallbacksOfFallbackParams(listOf(fallback(effortOnly)))
        if (toolChoiceType != "auto") {
            compactionRequest.toolChoice(JsonMissing.of())
        }
        assertThat(requests.firstValue).isEqualTo(compactionRequest.build())
        assertThat(requests.secondValue)
            .isEqualTo(params.toBuilder().messages(listOf(compactionResponse.toParam())).build())
    }

    @Test
    fun compactBeforeNextTurn_whenTurnPaused_waitsForResumedTurn() {
        val toolRunner = newToolRunner(maxIterations = 4)
        val pausedMessage = pausedServerToolUseMessage()
        val toolUseMessage = toolUseMessage("Paused Then Compacted City")
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(pausedMessage, toolUseMessage, compactionResponse, finalAssistantMessage())

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.PAUSE_TURN) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(4)).create(requests.capture(), any())
        val (_, resumed, compaction, after) = requests.allValues
        assertThat(resumed)
            .isEqualTo(initialMessageParams.toBuilder().addMessage(pausedMessage).build())
        assertThat(compaction)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(pausedMessage)
                    .addMessage(toolUseMessage)
                    .addMessage(getWeatherToolResponse("Paused Then Compacted City"))
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
        assertThat(after.messages()).containsExactly(compactionResponse.toParam())
    }

    @Test
    fun compactBeforeNextTurn_whenFinalTurn_compactsThenStops() {
        // The final answer is also the last iteration allowed; the compaction still goes out.
        val toolRunner = newToolRunner(maxIterations = 1)
        val finalMessage = finalAssistantMessage()
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(finalMessage, compactionResponse)

        val messages = mutableListOf<BetaMessage>()
        for (message in toolRunner) {
            messages.add(message)
            if (message.stopReason().get() == BetaStopReason.END_TURN) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        assertThat(messages).containsExactly(finalMessage, compactionResponse)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(finalMessage)
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
        assertThat(toolRunner.params().messages()).containsExactly(compactionResponse.toParam())
    }

    @ParameterizedTest
    @ValueSource(strings = ["max_tokens", "refusal"])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun compactBeforeNextTurn_whenFinalTurnCutOffWithToolUse_skipsAndWarns(stopReason: String) {
        val location = "Cut Off City ($stopReason)"
        val cutOffMessage =
            betaMessageBuilder()
                .addContent(getWeatherToolUse(location))
                .contextManagement(null)
                .stopReason(BetaStopReason.of(stopReason))
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(cutOffMessage, compactionResponse())

        val messages = mutableListOf<BetaMessage>()
        val stderr = captureStderr {
            for (message in toolRunner) {
                messages.add(message)
                toolRunner.compactBeforeNextTurn()
            }
        }

        assertThat(messages).containsExactly(cutOffMessage)
        verify(messageService, times(1)).create(any<MessageCreateParams>(), any())
        assertThat(stderr)
            .contains("pending compaction was skipped")
            .contains("stop_reason=$stopReason")
        assertThat(toolRunner.params()).isEqualTo(initialMessageParams)
        assertThat(GetWeather.executions).doesNotContainKey(location)
    }

    @Test
    fun compactBeforeNextTurn_whenFinalTurnCutOffWithoutToolUse_compacts() {
        val cutOffMessage =
            finalAssistantMessage().toBuilder().stopReason(BetaStopReason.MAX_TOKENS).build()
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(cutOffMessage, compactionResponse)

        val messages = mutableListOf<BetaMessage>()
        for (message in toolRunner) {
            messages.add(message)
            if (message.stopReason().get() == BetaStopReason.MAX_TOKENS) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        assertThat(messages).containsExactly(cutOffMessage, compactionResponse)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue.compaction()).isPresent
        assertThat(requests.secondValue.messages().last()).isEqualTo(cutOffMessage.toParam())
        assertThat(toolRunner.params().messages()).containsExactly(compactionResponse.toParam())
    }

    @Test
    fun compactBeforeNextTurn_whenFinalTurnHasBlockNewerThanSdk_compacts() {
        val newerBlock = """{"type":"newer_block","note":"kept"}"""
        val finalMessage =
            finalAssistantMessage()
                .toBuilder()
                .addContent(jsonMapper().readValue(newerBlock, BetaContentBlock::class.java))
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(finalMessage, compactionResponse())

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.END_TURN) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue.compaction()).isPresent
        val finalTurn = requests.secondValue.messages().last()
        assertThat(
                jsonMapper()
                    .writeValueAsString(finalTurn.content().betaContentBlockParams().get().last())
            )
            .isEqualTo(newerBlock)
    }

    @Test
    fun compactBeforeNextTurn_whenMaxIterationsEndsRun_dropsPendingCompaction() {
        val toolRunner = newToolRunner(maxIterations = 1)
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(toolUseMessage("Out Of Iterations City"), compactionResponse())

        for (message in toolRunner) {
            toolRunner.compactBeforeNextTurn()
        }

        verify(messageService, times(1)).create(any<MessageCreateParams>(), any())
    }

    @Test
    fun compactBeforeNextTurn_whenCalledOnCompactionResponse_isIgnored() {
        val toolRunner = newToolRunner(maxIterations = 4)
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Compacted Once City"),
                compactionResponse(),
                finalAssistantMessage(),
                compactionResponse(),
            )

        val stopReasons = mutableListOf<BetaStopReason>()
        for (message in toolRunner) {
            stopReasons.add(message.stopReason().get())
            if (message.stopReason().get() != BetaStopReason.END_TURN) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        assertThat(stopReasons)
            .containsExactly(
                BetaStopReason.TOOL_USE,
                BetaStopReason.COMPACTION,
                BetaStopReason.END_TURN,
            )
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        assertThat(requests.allValues.map { it.compaction().isPresent })
            .containsExactly(false, true, false)
    }

    @Test
    fun compaction_whenCompactedTwice_leavesOnlyNewerBlock() {
        val toolRunner = newToolRunner(maxIterations = 4)
        val firstCompactionResponse = compactionResponse("First summary.", "sig_01")
        val secondToolUseMessage = toolUseMessage("Compacted Twice City")
        val secondCompactionResponse = compactionResponse("Second summary.", "sig_02")
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Compacted Twice City"),
                firstCompactionResponse,
                secondToolUseMessage,
                secondCompactionResponse,
                finalAssistantMessage(),
            )

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(5)).create(requests.capture(), any())
        // The second compaction summarizes the first block and what followed it...
        assertThat(requests.allValues[3].compaction()).isPresent
        assertThat(requests.allValues[3].messages())
            .containsExactly(
                firstCompactionResponse.toParam(),
                secondToolUseMessage.toParam(),
                getWeatherToolResponse("Compacted Twice City"),
            )
        // ...and its block then stands alone.
        assertThat(requests.allValues[4].messages())
            .containsExactly(secondCompactionResponse.toParam())
    }

    @Test
    fun compaction_whenResponseHasBlockNewerThanSdk_sendsResponseBackAsItCame() {
        val content =
            """
            [
              {
                "type": "compaction",
                "content": "Summary so far.",
                "encrypted_content": null,
                "signature": "sig_01"
              },
              {"type": "block_from_the_future", "tools": [], "name": "docs"}
            ]
            """
        val compactionResponse =
            compactionResponse()
                .toBuilder()
                .content(jsonMapper().readValue(content, jacksonTypeRef<List<BetaContentBlock>>()))
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Newer Block City"),
                compactionResponse,
                finalAssistantMessage(),
            )

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        val history = requests.thirdValue.messages()
        assertThat(jsonMapper().valueToTree<JsonNode>(history))
            .isEqualTo(jsonMapper().readTree("""[{"content": $content, "role": "assistant"}]"""))
        // Key order included.
        assertThat(
                jsonMapper()
                    .writeValueAsString(
                        history.single().content().betaContentBlockParams().get().last()
                    )
            )
            .isEqualTo("""{"type":"block_from_the_future","tools":[],"name":"docs"}""")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    @ResourceLock(Resources.SYSTEM_ERR)
    fun compaction_whenNoSummary_keepsHistoryAndWarns(hasBlock: Boolean) {
        val toolRunner = newToolRunner(maxIterations = 4)
        val toolUseMessage = toolUseMessage("Not Compacted City")
        // Either a compaction block without content, or no content at all and the summarization's
        // own stop reason.
        val notCompactedResponse =
            betaMessageBuilder()
                .content(
                    if (hasBlock)
                        listOf(
                            BetaContentBlock.ofCompaction(
                                BetaCompactionBlock.builder()
                                    .content(null)
                                    .encryptedContent(null)
                                    .build()
                            )
                        )
                    else listOf()
                )
                .contextManagement(null)
                .stopReason(if (hasBlock) BetaStopReason.COMPACTION else BetaStopReason.MAX_TOKENS)
                .build()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(toolUseMessage, notCompactedResponse, finalAssistantMessage())

        var yielded = 0
        val stderr = captureStderr {
            for (message in toolRunner) {
                // The second call is made on the compaction response, so it is ignored: no retry
                // is sent.
                if (++yielded <= 2) {
                    toolRunner.compactBeforeNextTurn()
                }
            }
        }

        assertThat(stderr).contains("Compaction produced no summary")
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        assertThat(requests.thirdValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(toolUseMessage)
                    .addMessage(getWeatherToolResponse("Not Compacted City"))
                    .build()
            )
    }

    @Test
    fun compaction_whenRequestThrows_clearsCompaction() {
        val failure = AnthropicIoException("Connection reset")
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(toolUseMessage("Failed Compaction City"))
            .thenThrow(failure)
        val iterator = toolRunner.iterator()

        iterator.next()
        toolRunner.compactBeforeNextTurn()
        val exception = assertThrows<AnthropicIoException> { iterator.hasNext() }

        assertThat(exception).isSameAs(failure)
        verify(messageService, times(2)).create(any<MessageCreateParams>(), any())
        // Nothing is being compacted any more, so the messages can be replaced again.
        toolRunner.setNextParams(
            toolRunner.params().toBuilder().addUserMessage("And in NYC?").build()
        )
    }

    @Test
    fun setNextParams_whenCompacting_refusesDifferentMessages() {
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Compacting City"),
                compactionResponse,
                finalAssistantMessage(),
            )

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn()
            } else if (message.stopReason().get() == BetaStopReason.COMPACTION) {
                val exception =
                    assertThrows<IllegalStateException> {
                        toolRunner.setNextParams(
                            toolRunner.params().toBuilder().addUserMessage("And in NYC?").build()
                        )
                    }
                assertThat(exception)
                    .hasMessageContaining("while the conversation is being compacted")
                // Other params can still change, and the change is kept after the history is
                // replaced.
                toolRunner.setNextParams(toolRunner.params().toBuilder().maxTokens(2048).build())
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        assertThat(requests.thirdValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .maxTokens(2048)
                    .messages(listOf(compactionResponse.toParam()))
                    .build()
            )
    }

    @Test
    fun setNextParams_whenCompacting_refusesCompactionEdit() {
        val compactionResponse = compactionResponse()
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Compacting With Edit City"),
                compactionResponse,
                finalAssistantMessage(),
            )

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn()
            } else if (message.stopReason().get() == BetaStopReason.COMPACTION) {
                val exception =
                    assertThrows<IllegalStateException> {
                        toolRunner.setNextParams(
                            toolRunner
                                .params()
                                .toBuilder()
                                .contextManagement(
                                    BetaContextManagementConfig.builder()
                                        .addEdit(BetaCompact20260112Edit.builder().build())
                                        .build()
                                )
                                .build()
                        )
                    }
                assertThat(exception).hasMessageContaining("has a compaction edit")
            }
        }

        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).create(requests.capture(), any())
        assertThat(requests.thirdValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .messages(listOf(compactionResponse.toParam()))
                    .build()
            )
    }

    @Test
    fun lastToolResponse_whenHistoryCompacted_returnsEmptyOptional() {
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(
                toolUseMessage("Summarized Away City"),
                compactionResponse(),
                finalAssistantMessage(),
            )

        for (message in toolRunner) {
            if (message.stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        // The tool response was summarized away with the rest of the history.
        assertThat(toolRunner.lastToolResponse()).isEmpty
    }

    @Test
    fun toolRunner_whenParamsSetCompaction_throws() {
        val paramsWithCompaction =
            initialMessageParams
                .toBuilder()
                .compaction(BetaCompactionConfig.builder().build())
                .build()

        val fromConstructor =
            assertThrows<IllegalArgumentException> { newToolRunner(paramsWithCompaction) }
        val fromSetter =
            assertThrows<IllegalArgumentException> {
                toolRunner.setNextParams(paramsWithCompaction)
            }

        assertThat(fromConstructor)
            .hasMessage(
                "`compaction` cannot be set on a tool runner: every request in the loop would " +
                    "compact again. Call `compactBeforeNextTurn()` on the tool runner when the " +
                    "conversation should be compacted instead."
            )
        assertThat(fromSetter).hasMessage(fromConstructor.message)
    }

    @Test
    fun compactBeforeNextTurn_whenContextManagementHasCompactionEdit_throws() {
        val paramsWithCompactionEdit =
            initialMessageParams
                .toBuilder()
                .contextManagement(
                    BetaContextManagementConfig.builder()
                        .addEdit(BetaCompact20260112Edit.builder().build())
                        .build()
                )
                .build()

        val exception =
            assertThrows<IllegalStateException> {
                newToolRunner(paramsWithCompactionEdit).compactBeforeNextTurn()
            }

        assertThat(exception).hasMessageContaining("has a compaction edit")
    }

    @Test
    fun setNextParams_whenCompactionScheduled_refusesCompactionEdit() {
        val toolUseMessage = toolUseMessage("Compaction Edit City")
        whenever(messageService.create(any<MessageCreateParams>(), any()))
            .thenReturn(toolUseMessage, compactionResponse())
        val iterator = toolRunner.iterator()

        iterator.next()
        toolRunner.compactBeforeNextTurn()
        val exception =
            assertThrows<IllegalStateException> {
                toolRunner.setNextParams(
                    toolRunner
                        .params()
                        .toBuilder()
                        .addMessage(toolUseMessage)
                        .addMessage(getWeatherToolResponse("Compaction Edit City"))
                        .contextManagement(
                            BetaContextManagementConfig.builder()
                                .addEdit(BetaCompact20260112Edit.builder().build())
                                .build()
                        )
                        .build()
                )
            }

        assertThat(exception).hasMessageContaining("has a compaction edit")
        // The refused params were not kept, so the compaction goes out as scheduled.
        iterator.next()
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).create(requests.capture(), any())
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(toolUseMessage)
                    .addMessage(getWeatherToolResponse("Compaction Edit City"))
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
    }

    @Test
    fun streamingCompactBeforeNextTurn_whenToolUse_compactsAfterToolResults() {
        val toolRunner = newToolRunner(maxIterations = 2)
        val compactionEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofCompaction(
                    BetaCompactionBlock.builder()
                        .content(null)
                        .encryptedContent(null)
                        .signature("sig_01")
                        .build()
                ),
                BetaRawContentBlockDelta.ofCompaction(
                    BetaCompactionContentBlockDelta.builder()
                        .content("Summary so far.")
                        .encryptedContent(null)
                        .build()
                ),
                BetaStopReason.COMPACTION,
            )
        val finalEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofText(
                    BetaTextBlock.builder().citations(null).text("").build()
                ),
                BetaRawContentBlockDelta.ofText(
                    BetaTextDelta.builder().text("Foggy, as usual.").build()
                ),
                BetaStopReason.END_TURN,
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(
                streamResponseOf(getWeatherToolUseStreamEvents("Streamed Compacted City")),
                streamResponseOf(compactionEvents),
                streamResponseOf(finalEvents),
            )

        val messages = mutableListOf<BetaMessage>()
        for (response in toolRunner.streaming()) {
            val accumulator = BetaMessageAccumulator.create()
            response.stream().forEach(accumulator::accumulate)
            messages.add(accumulator.message())
            if (messages.last().stopReason().get() == BetaStopReason.TOOL_USE) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        assertThat(messages.map { it.stopReason().get() })
            .containsExactly(
                BetaStopReason.TOOL_USE,
                BetaStopReason.COMPACTION,
                BetaStopReason.END_TURN,
            )
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(3)).createStreaming(requests.capture(), any())
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(messages[0])
                    .addMessage(getWeatherToolResponse("Streamed Compacted City"))
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
        assertThat(requests.thirdValue)
            .isEqualTo(
                initialMessageParams.toBuilder().messages(listOf(messages[1].toParam())).build()
            )
        assertThat(messages[1].toParam().content().betaContentBlockParams().get().single())
            .isEqualTo(
                BetaContentBlockParam.ofCompaction(
                    BetaCompactionBlockParam.builder()
                        .content("Summary so far.")
                        .encryptedContent(null)
                        .signature("sig_01")
                        .build()
                )
            )
    }

    @Test
    fun streamingCompactBeforeNextTurn_whenFinalTurn_compactsThenStops() {
        val toolRunner = newToolRunner(maxIterations = 1)
        val finalEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofText(
                    BetaTextBlock.builder().citations(null).text("").build()
                ),
                BetaRawContentBlockDelta.ofText(
                    BetaTextDelta.builder().text("Foggy, as usual.").build()
                ),
                BetaStopReason.END_TURN,
            )
        val compactionEvents =
            streamEvents(
                BetaRawContentBlockStartEvent.ContentBlock.ofCompaction(
                    BetaCompactionBlock.builder().content(null).encryptedContent(null).build()
                ),
                BetaRawContentBlockDelta.ofCompaction(
                    BetaCompactionContentBlockDelta.builder()
                        .content("Summary so far.")
                        .encryptedContent(null)
                        .build()
                ),
                BetaStopReason.COMPACTION,
            )
        whenever(messageService.createStreaming(any<MessageCreateParams>(), any()))
            .thenReturn(streamResponseOf(finalEvents), streamResponseOf(compactionEvents))

        val messages = mutableListOf<BetaMessage>()
        for (response in toolRunner.streaming()) {
            val accumulator = BetaMessageAccumulator.create()
            response.stream().forEach(accumulator::accumulate)
            messages.add(accumulator.message())
            if (messages.last().stopReason().get() == BetaStopReason.END_TURN) {
                toolRunner.compactBeforeNextTurn()
            }
        }

        assertThat(messages.map { it.stopReason().get() })
            .containsExactly(BetaStopReason.END_TURN, BetaStopReason.COMPACTION)
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(2)).createStreaming(requests.capture(), any())
        assertThat(requests.secondValue)
            .isEqualTo(
                initialMessageParams
                    .toBuilder()
                    .addMessage(messages[0])
                    .compaction(BetaCompactionConfig.builder().build())
                    .build()
            )
        assertThat(toolRunner.params().messages()).containsExactly(messages[1].toParam())
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
                .stopReason(BetaStopReason.END_TURN)
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
                .stopReason(BetaStopReason.END_TURN)
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
                .stopReason(BetaStopReason.END_TURN)
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

    @Test
    fun iteration_whenToolRemovedViaSetNextParams_respondsWithNotFoundError() {
        // The `tool_removal` reaches the runner only through `setNextParams`, not the initial
        // params, so this proves the availability fold reads the mutated params on later turns.
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(initialMessageParams)
                    .maxIterations(3)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId1")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Removal Mutation Turn City")))
                        .build()
                )
                .contextManagement(null)
                .build()
        // The caller answers `assistantMessage1` itself and removes the tool for the rest of the
        // run; setting the next params also stops the runner from executing this turn's tools.
        val paramsWithRemoval =
            initialMessageParams
                .toBuilder()
                .addMessage(assistantMessage1)
                .addUserMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId1")
                                .content("The weather is whatever I want!")
                                .build()
                        )
                    )
                )
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolRemoval(
                            BetaRequestToolRemovalBlock.builder()
                                .referenceTool("get_weather")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId2")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Removed Mid-run City")))
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
                                .toolUseId("toolUseId2")
                                .content("Error: Tool 'get_weather' not found")
                                .isError(true)
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
                        .text("The weather in Removed Mid-run City is 404!")
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(messageService.create(paramsWithRemoval, requestOptions))
            .thenReturn(assistantMessage2)
        whenever(
                messageService.create(
                    paramsWithRemoval
                        .toBuilder()
                        .addMessage(assistantMessage2)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage3)

        val messages =
            toolRunner
                .asSequence()
                .onEachIndexed { index, _ ->
                    if (index == 0) {
                        toolRunner.setNextParams(paramsWithRemoval)
                    }
                }
                .toList()

        assertThat(messages)
            .containsExactly(assistantMessage1, assistantMessage2, assistantMessage3)
        // Neither the turn whose params were replaced nor the `tool_use` for the removed tool
        // ran the tool.
        assertThat(GetWeather.executions).doesNotContainKey("Removal Mutation Turn City")
        assertThat(GetWeather.executions).doesNotContainKey("Removed Mid-run City")
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    @Test
    fun iteration_whenToolReAddedViaSetNextParams_executesTool() {
        // A `tool_addition` following a `tool_removal`, both supplied through `setNextParams`,
        // re-enables execution on the following turn.
        val toolRunner =
            BetaToolRunner(
                messageService,
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(initialMessageParams)
                    .maxIterations(3)
                    .build(),
                requestOptions,
            )
        val assistantMessage1 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId1")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Re-add Mutation Turn City")))
                        .build()
                )
                .contextManagement(null)
                .build()
        val paramsWithChanges =
            initialMessageParams
                .toBuilder()
                .addMessage(assistantMessage1)
                .addUserMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolResult(
                            BetaToolResultBlockParam.builder()
                                .toolUseId("toolUseId1")
                                .content("The weather is whatever I want!")
                                .build()
                        )
                    )
                )
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolRemoval(
                            BetaRequestToolRemovalBlock.builder()
                                .referenceTool("get_weather")
                                .build()
                        )
                    )
                )
                .addSystemMessageOfBetaContentBlockParams(
                    listOf(
                        BetaContentBlockParam.ofToolAddition(
                            BetaRequestToolAdditionBlock.builder()
                                .referenceTool("get_weather")
                                .build()
                        )
                    )
                )
                .build()
        val assistantMessage2 =
            betaMessageBuilder()
                .addContent(
                    BetaToolUseBlock.builder()
                        .id("toolUseId2")
                        .name("get_weather")
                        .input(JsonValue.from(mapOf("location" to "Re-added Mid-run City")))
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
                                .toolUseId("toolUseId2")
                                .content("The weather in Re-added Mid-run City is foggy and 60°F")
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
                        .text("The weather in Re-added Mid-run City is foggy and 60°F!")
                        .build()
                )
                .contextManagement(null)
                .stopReason(BetaStopReason.END_TURN)
                .build()
        whenever(messageService.create(initialMessageParams, requestOptions))
            .thenReturn(assistantMessage1)
        whenever(messageService.create(paramsWithChanges, requestOptions))
            .thenReturn(assistantMessage2)
        whenever(
                messageService.create(
                    paramsWithChanges
                        .toBuilder()
                        .addMessage(assistantMessage2)
                        .addMessage(expectedToolResponseMessageParam)
                        .build(),
                    requestOptions,
                )
            )
            .thenReturn(assistantMessage3)

        val messages =
            toolRunner
                .asSequence()
                .onEachIndexed { index, _ ->
                    if (index == 0) {
                        toolRunner.setNextParams(paramsWithChanges)
                    }
                }
                .toList()

        assertThat(messages)
            .containsExactly(assistantMessage1, assistantMessage2, assistantMessage3)
        assertThat(GetWeather.executions).doesNotContainKey("Re-add Mutation Turn City")
        assertThat(GetWeather.executions).containsEntry("Re-added Mid-run City", 1)
        assertThat(toolRunner.lastToolResponse()).hasValue(expectedToolResponseMessageParam)
    }

    private fun newToolRunner(
        params: MessageCreateParams = initialMessageParams,
        maxIterations: Long = 2,
    ) =
        BetaToolRunner(
            messageService,
            ToolRunnerCreateParams.builder()
                .initialMessageParams(params)
                .maxIterations(maxIterations)
                .build(),
            requestOptions,
        )

    private fun toolUseMessage(location: String) =
        betaMessageBuilder().addContent(getWeatherToolUse(location)).contextManagement(null).build()

    private fun compactionResponse(
        summary: String = "Summary so far.",
        signature: String = "sig_01",
    ) =
        betaMessageBuilder()
            .addContent(
                BetaCompactionBlock.builder()
                    .content(summary)
                    .encryptedContent(null)
                    .signature(signature)
                    .build()
            )
            .contextManagement(null)
            .stopReason(BetaStopReason.COMPACTION)
            .build()

    /** Runs [handle] while each message is being handled, before the runner runs its tool calls. */
    private fun BetaToolRunner.runHandlingEachMessage(handle: (index: Int) -> Unit) =
        asSequence().forEachIndexed { index, _ -> handle(index) }

    private fun sentRequests(count: Int): List<MessageCreateParams> {
        val requests = argumentCaptor<MessageCreateParams>()
        verify(messageService, times(count)).create(requests.capture(), any())
        return requests.allValues
    }

    private fun toolAddition(definition: BetaToolUnion) =
        BetaContentBlockParam.ofToolAddition(
            BetaRequestToolAdditionBlock.builder().definitionTool(definition).build()
        )

    private fun toolAddition(toolParametersType: Class<*>) =
        toolAddition(BetaToolUnion.ofBetaTool(toolFromClass(toolParametersType)))

    private fun toolRemoval(name: String) =
        BetaContentBlockParam.ofToolRemoval(
            BetaRequestToolRemovalBlock.builder().referenceTool(name).build()
        )

    private fun toolUse(name: String, location: String, id: String = "toolUseId") =
        BetaToolUseBlock.builder()
            .id(id)
            .name(name)
            .input(JsonValue.from(mapOf("location" to location)))
            .build()

    private fun toolResult(content: String, id: String = "toolUseId") =
        BetaContentBlockParam.ofToolResult(
            BetaToolResultBlockParam.builder().toolUseId(id).content(content).build()
        )

    private fun toolNotFoundResult(name: String, id: String = "toolUseId") =
        BetaContentBlockParam.ofToolResult(
            BetaToolResultBlockParam.builder()
                .toolUseId(id)
                .content("Error: Tool '$name' not found")
                .isError(true)
                .build()
        )

    private fun toolResponse(content: String) =
        BetaMessageParam.builder()
            .role(BetaMessageParam.Role.USER)
            .contentOfBetaContentBlockParams(listOf(toolResult(content)))
            .build()

    private fun toolNotFoundResponse(name: String) =
        BetaMessageParam.builder()
            .role(BetaMessageParam.Role.USER)
            .contentOfBetaContentBlockParams(listOf(toolNotFoundResult(name)))
            .build()

    private fun getWeatherToolUse(location: String) =
        BetaToolUseBlock.builder()
            .id("toolUseId")
            .name("get_weather")
            .input(JsonValue.from(mapOf("location" to location)))
            .build()

    private fun getWeatherToolResponse(location: String) =
        BetaMessageParam.builder()
            .role(BetaMessageParam.Role.USER)
            .contentOfBetaContentBlockParams(
                listOf(
                    BetaContentBlockParam.ofToolResult(
                        BetaToolResultBlockParam.builder()
                            .toolUseId("toolUseId")
                            .content("The weather in $location is foggy and 60°F")
                            .build()
                    )
                )
            )
            .build()

    private fun getWeatherToolUseStreamEvents(location: String) =
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
                            .partialJson("""{"location":"$location"}""")
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
                            .stopReason(BetaStopReason.TOOL_USE)
                            .stopSequence(null)
                            .build()
                    )
                    .usage(
                        BetaMessageDeltaUsage.builder()
                            .fallbackCredit(null)
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

    private fun finalAssistantMessage() =
        betaMessageBuilder()
            .addContent(BetaTextBlock.builder().citations(null).text("Foggy, as usual.").build())
            .contextManagement(null)
            .stopReason(BetaStopReason.END_TURN)
            .build()

    private fun betaContainer(id: String) =
        BetaContainer.builder()
            .id(id)
            .expiresAt(OffsetDateTime.parse("2025-01-01T00:00:00Z"))
            .skills(null)
            .build()

    private fun streamResponseOf(events: List<BetaRawMessageStreamEvent>) =
        object : StreamResponse<BetaRawMessageStreamEvent> {
            override fun stream(): Stream<BetaRawMessageStreamEvent> = events.stream()

            override fun close() {}
        }

    private fun betaMessageBuilder() =
        BetaMessage.builder()
            .id("id")
            .model(Model.CLAUDE_SONNET_4_5)
            .container(null)
            .diagnostics(null)
            .stopDetails(null)
            // The stop reason of a turn that calls tools; turns that end otherwise override it.
            .stopReason(BetaStopReason.TOOL_USE)
            .stopSequence(null)
            .usage(betaUsage())

    private fun betaUsage() =
        BetaUsage.builder()
            .fallbackCredit(null)
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

    private fun pausedServerToolUseMessage() =
        betaMessageBuilder()
            .addContent(
                BetaTextBlock.builder().citations(null).text("Let me look that up.").build()
            )
            .addContent(
                BetaServerToolUseBlock.builder()
                    .id("srvtoolu_1")
                    .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                    .input(JsonValue.from(mapOf("query" to "weather in San Francisco")))
                    .build()
            )
            .contextManagement(null)
            .stopReason(BetaStopReason.PAUSE_TURN)
            .build()

    /** The event sequence for a streamed message with a single content block. */
    private fun streamEvents(
        contentBlock: BetaRawContentBlockStartEvent.ContentBlock,
        delta: BetaRawContentBlockDelta,
        stopReason: BetaStopReason,
    ) =
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
                BetaRawContentBlockStartEvent.builder().index(0L).contentBlock(contentBlock).build()
            ),
            BetaRawMessageStreamEvent.ofContentBlockDelta(
                BetaRawContentBlockDeltaEvent.builder().index(0L).delta(delta).build()
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
                            .stopReason(stopReason)
                            .stopSequence(null)
                            .build()
                    )
                    .usage(
                        BetaMessageDeltaUsage.builder()
                            .fallbackCredit(null)
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

@JsonClassDescription("Get the time in a given location")
private class GetTime : Supplier<String> {
    @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
    lateinit var location: String

    override fun get(): String = "12:00 in $location"
}

@JsonTypeName("get_time")
@JsonClassDescription("Get the time in a given location")
private class SunnyGetTime : Supplier<String> {
    @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
    lateinit var location: String

    override fun get(): String = "noon in $location"
}

@JsonTypeName("get_weather")
@JsonClassDescription("Get the weather in a given location")
private class SunnyGetWeather : Supplier<String> {
    @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
    lateinit var location: String

    override fun get(): String = "The weather in $location is sunny"
}

// `"pattern"` is not a supported keyword, so local validation rejects the derived schema.
@Suppress("unused")
private class UnsupportedSchemaTool(@get:Schema(pattern = "unsupported") val s: String)
