package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaCurrency
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsServiceAccountExecutionIdentity
import com.anthropic.models.beta.sessions.BetaManagedAgentsBudgetLimit
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.anthropic.models.beta.sessions.BetaManagedAgentsServerToolUsage
import com.anthropic.models.beta.sessions.BetaManagedAgentsSessionAgent
import com.anthropic.models.beta.sessions.BetaManagedAgentsSessionUpdatedEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsSessionUsageEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemMessageEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsUserToolResultEvent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsSessionEventTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsUserMessageEvent.builder()
                .id("sevt_011CZkZGPp1iBcp4kaQSihUm")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val betaManagedAgentsSessionEvent = BetaManagedAgentsSessionEvent.ofUserMessage(userMessage)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("userMessage" to Optional.of(userMessage)))
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserMessage(
                BetaManagedAgentsUserMessageEvent.builder()
                    .id("sevt_011CZkZGPp1iBcp4kaQSihUm")
                    .addTextContent("Where is my order #1234?")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofUserInterrupt() {
        val userInterrupt =
            BetaManagedAgentsUserInterruptEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserInterrupt(userInterrupt)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("userInterrupt" to Optional.of(userInterrupt)))
    }

    @Test
    fun ofUserInterruptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserInterrupt(
                BetaManagedAgentsUserInterruptEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofUserToolConfirmation() {
        val userToolConfirmation =
            BetaManagedAgentsUserToolConfirmationEvent.builder()
                .id("id")
                .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                .toolUseId("tool_use_id")
                .denyMessage("deny_message")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserToolConfirmation(userToolConfirmation)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("userToolConfirmation" to Optional.of(userToolConfirmation)))
    }

    @Test
    fun ofUserToolConfirmationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserToolConfirmation(
                BetaManagedAgentsUserToolConfirmationEvent.builder()
                    .id("id")
                    .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                    .toolUseId("tool_use_id")
                    .denyMessage("deny_message")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofUserCustomToolResult() {
        val userCustomToolResult =
            BetaManagedAgentsUserCustomToolResultEvent.builder()
                .id("id")
                .customToolUseId("custom_tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserCustomToolResult(userCustomToolResult)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("userCustomToolResult" to Optional.of(userCustomToolResult)))
    }

    @Test
    fun ofUserCustomToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserCustomToolResult(
                BetaManagedAgentsUserCustomToolResultEvent.builder()
                    .id("id")
                    .customToolUseId("custom_tool_use_id")
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentCustomToolUse() {
        val agentCustomToolUse =
            BetaManagedAgentsAgentCustomToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentCustomToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentCustomToolUse(agentCustomToolUse)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentCustomToolUse" to Optional.of(agentCustomToolUse)))
    }

    @Test
    fun ofAgentCustomToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentCustomToolUse(
                BetaManagedAgentsAgentCustomToolUseEvent.builder()
                    .id("id")
                    .input(
                        BetaManagedAgentsAgentCustomToolUseEvent.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentMessage() {
        val agentMessage =
            BetaManagedAgentsAgentMessageEvent.builder()
                .id("sevt_011CZkZHPq1jCdq5mbRTjiVn")
                .addTextContent("Let me look up order #1234 for you.")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMessage(agentMessage)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentMessage" to Optional.of(agentMessage)))
    }

    @Test
    fun ofAgentMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMessage(
                BetaManagedAgentsAgentMessageEvent.builder()
                    .id("sevt_011CZkZHPq1jCdq5mbRTjiVn")
                    .addTextContent("Let me look up order #1234 for you.")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentThinking() {
        val agentThinking =
            BetaManagedAgentsAgentThinkingEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThinking(agentThinking)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentThinking" to Optional.of(agentThinking)))
    }

    @Test
    fun ofAgentThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThinking(
                BetaManagedAgentsAgentThinkingEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentMcpToolUse() {
        val agentMcpToolUse =
            BetaManagedAgentsAgentMcpToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentMcpToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .mcpServerName("mcp_server_name")
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .evaluatedPermission(BetaManagedAgentsAgentEvaluatedPermission.ALLOW)
                .evaluation(BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build())
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolUse(agentMcpToolUse)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentMcpToolUse" to Optional.of(agentMcpToolUse)))
    }

    @Test
    fun ofAgentMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolUse(
                BetaManagedAgentsAgentMcpToolUseEvent.builder()
                    .id("id")
                    .input(
                        BetaManagedAgentsAgentMcpToolUseEvent.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .mcpServerName("mcp_server_name")
                    .name("name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .evaluatedPermission(BetaManagedAgentsAgentEvaluatedPermission.ALLOW)
                    .evaluation(BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build())
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentMcpToolResult() {
        val agentMcpToolResult =
            BetaManagedAgentsAgentMcpToolResultEvent.builder()
                .id("id")
                .mcpToolUseId("mcp_tool_use_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolResult(agentMcpToolResult)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentMcpToolResult" to Optional.of(agentMcpToolResult)))
    }

    @Test
    fun ofAgentMcpToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentMcpToolResult(
                BetaManagedAgentsAgentMcpToolResultEvent.builder()
                    .id("id")
                    .mcpToolUseId("mcp_tool_use_id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentToolUse() {
        val agentToolUse =
            BetaManagedAgentsAgentToolUseEvent.builder()
                .id("id")
                .input(
                    BetaManagedAgentsAgentToolUseEvent.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .evaluatedPermission(BetaManagedAgentsAgentEvaluatedPermission.ALLOW)
                .evaluation(BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build())
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolUse(agentToolUse)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentToolUse" to Optional.of(agentToolUse)))
    }

    @Test
    fun ofAgentToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolUse(
                BetaManagedAgentsAgentToolUseEvent.builder()
                    .id("id")
                    .input(
                        BetaManagedAgentsAgentToolUseEvent.Input.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .evaluatedPermission(BetaManagedAgentsAgentEvaluatedPermission.ALLOW)
                    .evaluation(BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build())
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentToolResult() {
        val agentToolResult =
            BetaManagedAgentsAgentToolResultEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toolUseId("tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolResult(agentToolResult)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentToolResult" to Optional.of(agentToolResult)))
    }

    @Test
    fun ofAgentToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentToolResult(
                BetaManagedAgentsAgentToolResultEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .toolUseId("tool_use_id")
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentThreadMessageReceived() {
        val agentThreadMessageReceived =
            BetaManagedAgentsAgentThreadMessageReceivedEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .fromSessionThreadId("from_session_thread_id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .fromAgentName("from_agent_name")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageReceived(agentThreadMessageReceived)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf("agentThreadMessageReceived" to Optional.of(agentThreadMessageReceived))
            )
    }

    @Test
    fun ofAgentThreadMessageReceivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageReceived(
                BetaManagedAgentsAgentThreadMessageReceivedEvent.builder()
                    .id("id")
                    .addTextContent("Where is my order #1234?")
                    .fromSessionThreadId("from_session_thread_id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .fromAgentName("from_agent_name")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentThreadMessageSent() {
        val agentThreadMessageSent =
            BetaManagedAgentsAgentThreadMessageSentEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .toSessionThreadId("to_session_thread_id")
                .toAgentName("to_agent_name")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageSent(agentThreadMessageSent)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("agentThreadMessageSent" to Optional.of(agentThreadMessageSent)))
    }

    @Test
    fun ofAgentThreadMessageSentRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadMessageSent(
                BetaManagedAgentsAgentThreadMessageSentEvent.builder()
                    .id("id")
                    .addTextContent("Where is my order #1234?")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .toSessionThreadId("to_session_thread_id")
                    .toAgentName("to_agent_name")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofAgentThreadContextCompacted() {
        val agentThreadContextCompacted =
            BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadContextCompacted(agentThreadContextCompacted)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf("agentThreadContextCompacted" to Optional.of(agentThreadContextCompacted))
            )
    }

    @Test
    fun ofAgentThreadContextCompactedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofAgentThreadContextCompacted(
                BetaManagedAgentsAgentThreadContextCompactedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionError() {
        val sessionError =
            BetaManagedAgentsSessionErrorEvent.builder()
                .id("id")
                .error(
                    BetaManagedAgentsToolUseLimitReachedError.builder()
                        .message(
                            "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                        )
                        .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionError(sessionError)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionError" to Optional.of(sessionError)))
    }

    @Test
    fun ofSessionErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionError(
                BetaManagedAgentsSessionErrorEvent.builder()
                    .id("id")
                    .error(
                        BetaManagedAgentsToolUseLimitReachedError.builder()
                            .message(
                                "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                            )
                            .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                            .build()
                    )
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionStatusRescheduled() {
        val sessionStatusRescheduled =
            BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRescheduled(sessionStatusRescheduled)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionStatusRescheduled" to Optional.of(sessionStatusRescheduled)))
    }

    @Test
    fun ofSessionStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRescheduled(
                BetaManagedAgentsSessionStatusRescheduledEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionStatusRunning() {
        val sessionStatusRunning =
            BetaManagedAgentsSessionStatusRunningEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRunning(sessionStatusRunning)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionStatusRunning" to Optional.of(sessionStatusRunning)))
    }

    @Test
    fun ofSessionStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusRunning(
                BetaManagedAgentsSessionStatusRunningEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionStatusIdle() {
        val sessionStatusIdle =
            BetaManagedAgentsSessionStatusIdleEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .stopDetails(
                    BetaManagedAgentsSessionRefusalStopDetails.builder()
                        .category(BetaManagedAgentsSessionRefusalStopDetails.Category.CYBER)
                        .explanation("explanation")
                        .build()
                )
                .stopReason(BetaManagedAgentsSessionEndTurn.builder().build())
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusIdle(sessionStatusIdle)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionStatusIdle" to Optional.of(sessionStatusIdle)))
    }

    @Test
    fun ofSessionStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusIdle(
                BetaManagedAgentsSessionStatusIdleEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .stopDetails(
                        BetaManagedAgentsSessionRefusalStopDetails.builder()
                            .category(BetaManagedAgentsSessionRefusalStopDetails.Category.CYBER)
                            .explanation("explanation")
                            .build()
                    )
                    .stopReason(BetaManagedAgentsSessionEndTurn.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionStatusTerminated() {
        val sessionStatusTerminated =
            BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusTerminated(sessionStatusTerminated)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionStatusTerminated" to Optional.of(sessionStatusTerminated)))
    }

    @Test
    fun ofSessionStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionStatusTerminated(
                BetaManagedAgentsSessionStatusTerminatedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionThreadCreated() {
        val sessionThreadCreated =
            BetaManagedAgentsSessionThreadCreatedEvent.builder()
                .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                .workflowRunId(null)
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadCreated(sessionThreadCreated)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionThreadCreated" to Optional.of(sessionThreadCreated)))
    }

    @Test
    fun ofSessionThreadCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadCreated(
                BetaManagedAgentsSessionThreadCreatedEvent.builder()
                    .id("sevt_011CZkZWXb7pJkx1shYaqoCu")
                    .agentName("Researcher")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .sessionThreadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                    .workflowRunId(null)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSpanOutcomeEvaluationStart() {
        val spanOutcomeEvaluationStart =
            BetaManagedAgentsSpanOutcomeEvaluationStartEvent.builder()
                .id("sevt_011CZkZTUy4mGhu8peVXnmzr")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationStart(spanOutcomeEvaluationStart)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf("spanOutcomeEvaluationStart" to Optional.of(spanOutcomeEvaluationStart))
            )
    }

    @Test
    fun ofSpanOutcomeEvaluationStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationStart(
                BetaManagedAgentsSpanOutcomeEvaluationStartEvent.builder()
                    .id("sevt_011CZkZTUy4mGhu8peVXnmzr")
                    .iteration(0)
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSpanOutcomeEvaluationEnd() {
        val spanOutcomeEvaluationEnd =
            BetaManagedAgentsSpanOutcomeEvaluationEndEvent.builder()
                .id("sevt_011CZkZUVz5nHiv9qfWYomas")
                .explanation("All five sections present with inline citations.")
                .iteration(0)
                .outcomeEvaluationStartId("sevt_011CZkZTUy4mGhu8peVXnmzr")
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                .result("satisfied")
                .usage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(1536)
                        .inputTokens(1842)
                        .outputTokens(213)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationEnd(spanOutcomeEvaluationEnd)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("spanOutcomeEvaluationEnd" to Optional.of(spanOutcomeEvaluationEnd)))
    }

    @Test
    fun ofSpanOutcomeEvaluationEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationEnd(
                BetaManagedAgentsSpanOutcomeEvaluationEndEvent.builder()
                    .id("sevt_011CZkZUVz5nHiv9qfWYomas")
                    .explanation("All five sections present with inline citations.")
                    .iteration(0)
                    .outcomeEvaluationStartId("sevt_011CZkZTUy4mGhu8peVXnmzr")
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:31Z"))
                    .result("satisfied")
                    .usage(
                        BetaManagedAgentsSpanModelUsage.builder()
                            .cacheCreationInputTokens(0)
                            .cacheReadInputTokens(1536)
                            .inputTokens(1842)
                            .outputTokens(213)
                            .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSpanModelRequestStart() {
        val spanModelRequestStart =
            BetaManagedAgentsSpanModelRequestStartEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestStart(spanModelRequestStart)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("spanModelRequestStart" to Optional.of(spanModelRequestStart)))
    }

    @Test
    fun ofSpanModelRequestStartRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestStart(
                BetaManagedAgentsSpanModelRequestStartEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSpanModelRequestEnd() {
        val spanModelRequestEnd =
            BetaManagedAgentsSpanModelRequestEndEvent.builder()
                .id("id")
                .isError(true)
                .modelRequestStartId("model_request_start_id")
                .modelUsage(
                    BetaManagedAgentsSpanModelUsage.builder()
                        .cacheCreationInputTokens(0)
                        .cacheReadInputTokens(0)
                        .inputTokens(0)
                        .outputTokens(0)
                        .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestEnd(spanModelRequestEnd)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("spanModelRequestEnd" to Optional.of(spanModelRequestEnd)))
    }

    @Test
    fun ofSpanModelRequestEndRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanModelRequestEnd(
                BetaManagedAgentsSpanModelRequestEndEvent.builder()
                    .id("id")
                    .isError(true)
                    .modelRequestStartId("model_request_start_id")
                    .modelUsage(
                        BetaManagedAgentsSpanModelUsage.builder()
                            .cacheCreationInputTokens(0)
                            .cacheReadInputTokens(0)
                            .inputTokens(0)
                            .outputTokens(0)
                            .speed(BetaManagedAgentsSpanModelUsage.Speed.STANDARD)
                            .build()
                    )
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSpanOutcomeEvaluationOngoing() {
        val spanOutcomeEvaluationOngoing =
            BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.builder()
                .id("sevt_011CZkZbCG2uPpc6xmDfvTzh")
                .iteration(0)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationOngoing(
                spanOutcomeEvaluationOngoing
            )

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf("spanOutcomeEvaluationOngoing" to Optional.of(spanOutcomeEvaluationOngoing))
            )
    }

    @Test
    fun ofSpanOutcomeEvaluationOngoingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSpanOutcomeEvaluationOngoing(
                BetaManagedAgentsSpanOutcomeEvaluationOngoingEvent.builder()
                    .id("sevt_011CZkZbCG2uPpc6xmDfvTzh")
                    .iteration(0)
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofUserDefineOutcome() {
        val userDefineOutcome =
            BetaManagedAgentsUserDefineOutcomeEvent.builder()
                .id("sevt_011CZkZSTx3mFgt7odUWmkyq")
                .description("Produce a 2-page summary as summary.md")
                .maxIterations(3)
                .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                .textRubric("Must cover all five sections; cite sources inline.")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserDefineOutcome(userDefineOutcome)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("userDefineOutcome" to Optional.of(userDefineOutcome)))
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserDefineOutcome(
                BetaManagedAgentsUserDefineOutcomeEvent.builder()
                    .id("sevt_011CZkZSTx3mFgt7odUWmkyq")
                    .description("Produce a 2-page summary as summary.md")
                    .maxIterations(3)
                    .outcomeId("outc_011CZkZRSw2kEfs6ncTVmjxP")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:02:14Z"))
                    .textRubric("Must cover all five sections; cite sources inline.")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionDeleted() {
        val sessionDeleted =
            BetaManagedAgentsSessionDeletedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionDeleted(sessionDeleted)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionDeleted" to Optional.of(sessionDeleted)))
    }

    @Test
    fun ofSessionDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionDeleted(
                BetaManagedAgentsSessionDeletedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionThreadStatusRunning() {
        val sessionThreadStatusRunning =
            BetaManagedAgentsSessionThreadStatusRunningEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRunning(sessionThreadStatusRunning)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf("sessionThreadStatusRunning" to Optional.of(sessionThreadStatusRunning))
            )
    }

    @Test
    fun ofSessionThreadStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRunning(
                BetaManagedAgentsSessionThreadStatusRunningEvent.builder()
                    .id("id")
                    .agentName("agent_name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionThreadStatusIdle() {
        val sessionThreadStatusIdle =
            BetaManagedAgentsSessionThreadStatusIdleEvent.builder()
                .id("sevt_011CZkZXYc8qKmy2tiZbrpDv")
                .agentName("Researcher")
                .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                .sessionThreadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                .stopDetails(null)
                .stopReason(BetaManagedAgentsSessionEndTurn.builder().build())
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusIdle(sessionThreadStatusIdle)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionThreadStatusIdle" to Optional.of(sessionThreadStatusIdle)))
    }

    @Test
    fun ofSessionThreadStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusIdle(
                BetaManagedAgentsSessionThreadStatusIdleEvent.builder()
                    .id("sevt_011CZkZXYc8qKmy2tiZbrpDv")
                    .agentName("Researcher")
                    .processedAt(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
                    .sessionThreadId("sthr_011CZkZVWa6oJjw1rgXZpnBt")
                    .stopDetails(null)
                    .stopReason(BetaManagedAgentsSessionEndTurn.builder().build())
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionThreadStatusTerminated() {
        val sessionThreadStatusTerminated =
            BetaManagedAgentsSessionThreadStatusTerminatedEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusTerminated(
                sessionThreadStatusTerminated
            )

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf("sessionThreadStatusTerminated" to Optional.of(sessionThreadStatusTerminated))
            )
    }

    @Test
    fun ofSessionThreadStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusTerminated(
                BetaManagedAgentsSessionThreadStatusTerminatedEvent.builder()
                    .id("id")
                    .agentName("agent_name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofUserToolResult() {
        val userToolResult =
            BetaManagedAgentsUserToolResultEvent.builder()
                .id("id")
                .toolUseId("tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserToolResult(userToolResult)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("userToolResult" to Optional.of(userToolResult)))
    }

    @Test
    fun ofUserToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofUserToolResult(
                BetaManagedAgentsUserToolResultEvent.builder()
                    .id("id")
                    .toolUseId("tool_use_id")
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionThreadStatusRescheduled() {
        val sessionThreadStatusRescheduled =
            BetaManagedAgentsSessionThreadStatusRescheduledEvent.builder()
                .id("id")
                .agentName("agent_name")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRescheduled(
                sessionThreadStatusRescheduled
            )

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(
                mapOf(
                    "sessionThreadStatusRescheduled" to Optional.of(sessionThreadStatusRescheduled)
                )
            )
    }

    @Test
    fun ofSessionThreadStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionThreadStatusRescheduled(
                BetaManagedAgentsSessionThreadStatusRescheduledEvent.builder()
                    .id("id")
                    .agentName("agent_name")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionUpdated() {
        val sessionUpdated =
            BetaManagedAgentsSessionUpdatedEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .agent(
                    BetaManagedAgentsSessionAgent.builder()
                        .id("agent_011CZkYpogX7uDKUyvBTophP")
                        .description("A general-purpose starter agent.")
                        .executionIdentity(
                            BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                        )
                        .addMcpServer(
                            BetaManagedAgentsMcpServerUrlDefinition.builder()
                                .name("example-mcp")
                                .url("https://example-server.modelcontextprotocol.io/sse")
                                .build()
                        )
                        .model(
                            BetaManagedAgentsModelConfig.builder()
                                .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                .effort(BetaManagedAgentsEffortLow.builder().build())
                                .inferenceGeo("inference_geo")
                                .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                .build()
                        )
                        .multiagent(null)
                        .name("My First Agent")
                        .addSkill(
                            BetaManagedAgentsAnthropicSkill.builder()
                                .skillId("xlsx")
                                .version("1")
                                .build()
                        )
                        .addSkill(
                            BetaManagedAgentsCustomSkill.builder()
                                .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                                .version("2")
                                .build()
                        )
                        .system(
                            "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                        )
                        .addTool(
                            BetaManagedAgentsAgentToolset20260401.builder()
                                .configs(listOf())
                                .defaultConfig(
                                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                        )
                                        .build()
                                )
                                .build()
                        )
                        .version(1)
                        .build()
                )
                .budget(
                    BetaManagedAgentsBudgetLimit.of(
                        BetaMonetaryAmount.builder()
                            .amount("2500")
                            .currency(BetaCurrency.USD)
                            .build()
                    )
                )
                .metadata(
                    BetaManagedAgentsSessionUpdatedEvent.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .title("title")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionUpdated(sessionUpdated)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionUpdated" to Optional.of(sessionUpdated)))
    }

    @Test
    fun ofSessionUpdatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionUpdated(
                BetaManagedAgentsSessionUpdatedEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .agent(
                        BetaManagedAgentsSessionAgent.builder()
                            .id("agent_011CZkYpogX7uDKUyvBTophP")
                            .description("A general-purpose starter agent.")
                            .executionIdentity(
                                BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                            )
                            .addMcpServer(
                                BetaManagedAgentsMcpServerUrlDefinition.builder()
                                    .name("example-mcp")
                                    .url("https://example-server.modelcontextprotocol.io/sse")
                                    .build()
                            )
                            .model(
                                BetaManagedAgentsModelConfig.builder()
                                    .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                    .effort(BetaManagedAgentsEffortLow.builder().build())
                                    .inferenceGeo("inference_geo")
                                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                    .build()
                            )
                            .multiagent(null)
                            .name("My First Agent")
                            .addSkill(
                                BetaManagedAgentsAnthropicSkill.builder()
                                    .skillId("xlsx")
                                    .version("1")
                                    .build()
                            )
                            .addSkill(
                                BetaManagedAgentsCustomSkill.builder()
                                    .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                                    .version("2")
                                    .build()
                            )
                            .system(
                                "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                            )
                            .addTool(
                                BetaManagedAgentsAgentToolset20260401.builder()
                                    .configs(listOf())
                                    .defaultConfig(
                                        BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                            .enabled(true)
                                            .permissionPolicy(
                                                BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                            )
                                            .build()
                                    )
                                    .build()
                            )
                            .version(1)
                            .build()
                    )
                    .budget(
                        BetaManagedAgentsBudgetLimit.of(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                    )
                    .metadata(
                        BetaManagedAgentsSessionUpdatedEvent.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .title("title")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSystemMessage() {
        val systemMessage =
            BetaManagedAgentsSystemMessageEvent.builder()
                .id("id")
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSystemMessage(systemMessage)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("systemMessage" to Optional.of(systemMessage)))
    }

    @Test
    fun ofSystemMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSystemMessage(
                BetaManagedAgentsSystemMessageEvent.builder()
                    .id("id")
                    .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofSessionUsage() {
        val sessionUsage =
            BetaManagedAgentsSessionUsageEvent.builder()
                .id("id")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .usage(
                    BetaManagedAgentsSessionUsageSnapshot.builder()
                        .activeSeconds(0.0)
                        .cacheCreation(
                            BetaManagedAgentsCacheCreationUsage.builder()
                                .ephemeral1hInputTokens(0L)
                                .ephemeral5mInputTokens(0L)
                                .build()
                        )
                        .cacheReadInputTokens(0L)
                        .inputTokens(0L)
                        .listCost(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                        .outputTokens(0L)
                        .serverToolUse(
                            BetaManagedAgentsServerToolUsage.builder()
                                .webFetchRequests(0)
                                .webSearchRequests(3)
                                .build()
                        )
                        .build()
                )
                .budget(
                    BetaManagedAgentsBudgetLimit.of(
                        BetaMonetaryAmount.builder()
                            .amount("2500")
                            .currency(BetaCurrency.USD)
                            .build()
                    )
                )
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionUsage(sessionUsage)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("sessionUsage" to Optional.of(sessionUsage)))
    }

    @Test
    fun ofSessionUsageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofSessionUsage(
                BetaManagedAgentsSessionUsageEvent.builder()
                    .id("id")
                    .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .usage(
                        BetaManagedAgentsSessionUsageSnapshot.builder()
                            .activeSeconds(0.0)
                            .cacheCreation(
                                BetaManagedAgentsCacheCreationUsage.builder()
                                    .ephemeral1hInputTokens(0L)
                                    .ephemeral5mInputTokens(0L)
                                    .build()
                            )
                            .cacheReadInputTokens(0L)
                            .inputTokens(0L)
                            .listCost(
                                BetaMonetaryAmount.builder()
                                    .amount("2500")
                                    .currency(BetaCurrency.USD)
                                    .build()
                            )
                            .outputTokens(0L)
                            .serverToolUse(
                                BetaManagedAgentsServerToolUsage.builder()
                                    .webFetchRequests(0)
                                    .webSearchRequests(3)
                                    .build()
                            )
                            .build()
                    )
                    .budget(
                        BetaManagedAgentsBudgetLimit.of(
                            BetaMonetaryAmount.builder()
                                .amount("2500")
                                .currency(BetaCurrency.USD)
                                .build()
                        )
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunCreated() {
        val workflowRunCreated =
            BetaManagedAgentsWorkflowRunCreatedEvent.builder()
                .id("sevt_01JQ8Z6X8K2N4V7T9B3C5D1E")
                .description("Reads each vendor's pricing page and tabulates the plans.")
                .name("Compare the vendors")
                .addPhase(
                    BetaManagedAgentsWorkflowRunPhase.builder()
                        .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                        .description(null)
                        .name("Collect the sources")
                        .build()
                )
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.412Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunCreated(workflowRunCreated)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunCreated" to Optional.of(workflowRunCreated)))
    }

    @Test
    fun ofWorkflowRunCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunCreated(
                BetaManagedAgentsWorkflowRunCreatedEvent.builder()
                    .id("sevt_01JQ8Z6X8K2N4V7T9B3C5D1E")
                    .description("Reads each vendor's pricing page and tabulates the plans.")
                    .name("Compare the vendors")
                    .addPhase(
                        BetaManagedAgentsWorkflowRunPhase.builder()
                            .id("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                            .description(null)
                            .name("Collect the sources")
                            .build()
                    )
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.412Z"))
                    .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunStatusEnded() {
        val workflowRunStatusEnded =
            BetaManagedAgentsWorkflowRunStatusEndedEvent.builder()
                .id("sevt_01JQ8ZC1V5B7N9M1K3J5H7GA")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.337Z"))
                .result(BetaManagedAgentsWorkflowRunResultCompleted.builder().build())
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunStatusEnded(workflowRunStatusEnded)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunStatusEnded" to Optional.of(workflowRunStatusEnded)))
    }

    @Test
    fun ofWorkflowRunStatusEndedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunStatusEnded(
                BetaManagedAgentsWorkflowRunStatusEndedEvent.builder()
                    .id("sevt_01JQ8ZC1V5B7N9M1K3J5H7GA")
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.337Z"))
                    .result(BetaManagedAgentsWorkflowRunResultCompleted.builder().build())
                    .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunPhaseStarted() {
        val workflowRunPhaseStarted =
            BetaManagedAgentsWorkflowRunPhaseStartedEvent.builder()
                .id("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:14.020Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunPhaseStarted(workflowRunPhaseStarted)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunPhaseStarted" to Optional.of(workflowRunPhaseStarted)))
    }

    @Test
    fun ofWorkflowRunPhaseStartedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunPhaseStarted(
                BetaManagedAgentsWorkflowRunPhaseStartedEvent.builder()
                    .id("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:02:14.020Z"))
                    .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                    .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunPhaseEnded() {
        val workflowRunPhaseEnded =
            BetaManagedAgentsWorkflowRunPhaseEndedEvent.builder()
                .id("sevt_01JQ8ZB9W2Y4A6C8E1G2J4L6")
                .phaseStartedId("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:04:47.905Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunPhaseEnded(workflowRunPhaseEnded)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunPhaseEnded" to Optional.of(workflowRunPhaseEnded)))
    }

    @Test
    fun ofWorkflowRunPhaseEndedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunPhaseEnded(
                BetaManagedAgentsWorkflowRunPhaseEndedEvent.builder()
                    .id("sevt_01JQ8ZB9W2Y4A6C8E1G2J4L6")
                    .phaseStartedId("sevt_01JQ8Z7M3P5R7T9V1X3Z5B7D")
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:04:47.905Z"))
                    .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                    .workflowRunPhaseId("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunStatusRunning() {
        val workflowRunStatusRunning =
            BetaManagedAgentsWorkflowRunStatusRunningEvent.builder()
                .id("sevt_01JQ8Z6Y1M3P5R7T9V1X3Z5B")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.430Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunStatusRunning(workflowRunStatusRunning)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunStatusRunning" to Optional.of(workflowRunStatusRunning)))
    }

    @Test
    fun ofWorkflowRunStatusRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunStatusRunning(
                BetaManagedAgentsWorkflowRunStatusRunningEvent.builder()
                    .id("sevt_01JQ8Z6Y1M3P5R7T9V1X3Z5B")
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:02:11.430Z"))
                    .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunStatusIdle() {
        val workflowRunStatusIdle =
            BetaManagedAgentsWorkflowRunStatusIdleEvent.builder()
                .id("sevt_01JQ8Z9A4C6E8G1J2L4N6Q8S")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:03:20.118Z"))
                .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunStatusIdle(workflowRunStatusIdle)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunStatusIdle" to Optional.of(workflowRunStatusIdle)))
    }

    @Test
    fun ofWorkflowRunStatusIdleRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunStatusIdle(
                BetaManagedAgentsWorkflowRunStatusIdleEvent.builder()
                    .id("sevt_01JQ8Z9A4C6E8G1J2L4N6Q8S")
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:03:20.118Z"))
                    .workflowRunId("wrun_011CZm3vQ8pKx2Lr7Nq9TbYd")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun ofWorkflowRunError() {
        val workflowRunError =
            BetaManagedAgentsWorkflowRunErrorEvent.builder()
                .id("sevt_01JQ8ZB7T3X5Z7C9E1G3J5L7")
                .programError("The workflow run's plan failed.")
                .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.301Z"))
                .workflowRunId("wrun_011CZm5tR2nHw6Jc9Ys4PdKf")
                .build()

        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunError(workflowRunError)

        assertThat(presentVariants(betaManagedAgentsSessionEvent))
            .isEqualTo(mapOf("workflowRunError" to Optional.of(workflowRunError)))
    }

    @Test
    fun ofWorkflowRunErrorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionEvent =
            BetaManagedAgentsSessionEvent.ofWorkflowRunError(
                BetaManagedAgentsWorkflowRunErrorEvent.builder()
                    .id("sevt_01JQ8ZB7T3X5Z7C9E1G3J5L7")
                    .programError("The workflow run's plan failed.")
                    .processedAt(OffsetDateTime.parse("2026-10-01T18:05:02.301Z"))
                    .workflowRunId("wrun_011CZm5tR2nHw6Jc9Ys4PdKf")
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionEvent),
                jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionEvent)
            .isEqualTo(betaManagedAgentsSessionEvent)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsSessionEvent =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to "sevt_011CZkZGPp1iBcp4kaQSihUm",
                            "processed_at" to "2026-03-15T10:00:00Z",
                            "session_thread_id" to "session_thread_id",
                            "tool_use_id" to "tool_use_id",
                            "is_error" to true,
                            "name" to "name",
                            "evaluated_permission" to "allow",
                            "evaluation" to mapOf("type" to "always_allow"),
                            "stop_details" to
                                mapOf(
                                    "category" to "cyber",
                                    "explanation" to "explanation",
                                    "type" to "refusal",
                                ),
                            "agent_name" to "Researcher",
                            "workflow_run_id" to null,
                            "iteration" to 0,
                            "outcome_id" to "outc_011CZkZRSw2kEfs6ncTVmjxP",
                            "description" to "Produce a 2-page summary as summary.md",
                            "budget" to
                                mapOf(
                                    "max_list_cost" to
                                        mapOf("amount" to "2500", "currency" to "USD"),
                                    "type" to "limit",
                                ),
                            "workflow_run_phase_id" to "wrph_011CZm4Kq7RtY2Wn8Vx3LbHd",
                        )
                    ),
                    jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsSessionEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsSessionEvent.id()).isEqualTo("sevt_011CZkZGPp1iBcp4kaQSihUm")
        assertThat(betaManagedAgentsSessionEvent.processedAt())
            .contains(OffsetDateTime.parse("2026-03-15T10:00:00Z"))
        assertThat(betaManagedAgentsSessionEvent.sessionThreadId()).contains("session_thread_id")
        assertThat(betaManagedAgentsSessionEvent.toolUseId()).contains("tool_use_id")
        assertThat(betaManagedAgentsSessionEvent.isError()).contains(true)
        assertThat(betaManagedAgentsSessionEvent.name()).contains("name")
        assertThat(betaManagedAgentsSessionEvent.evaluatedPermission())
            .contains(BetaManagedAgentsAgentEvaluatedPermission.ALLOW)
        assertThat(betaManagedAgentsSessionEvent.evaluation())
            .contains(
                BetaManagedAgentsAgentToolEvaluation.ofAlwaysAllow(
                    BetaManagedAgentsAgentToolEvaluationAlwaysAllow.builder().build()
                )
            )
        assertThat(betaManagedAgentsSessionEvent.stopDetails())
            .contains(
                BetaManagedAgentsSessionRefusalStopDetails.builder()
                    .category(BetaManagedAgentsSessionRefusalStopDetails.Category.CYBER)
                    .explanation("explanation")
                    .build()
            )
        assertThat(betaManagedAgentsSessionEvent.agentName()).contains("Researcher")
        assertThat(betaManagedAgentsSessionEvent.workflowRunId()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.iteration()).contains(0)
        assertThat(betaManagedAgentsSessionEvent.outcomeId())
            .contains("outc_011CZkZRSw2kEfs6ncTVmjxP")
        assertThat(betaManagedAgentsSessionEvent.description())
            .contains("Produce a 2-page summary as summary.md")
        assertThat(betaManagedAgentsSessionEvent.budget())
            .contains(
                BetaManagedAgentsBudgetLimit.of(
                    BetaMonetaryAmount.builder().amount("2500").currency(BetaCurrency.USD).build()
                )
            )
        assertThat(betaManagedAgentsSessionEvent.workflowRunPhaseId())
            .contains("wrph_011CZm4Kq7RtY2Wn8Vx3LbHd")

        val mismatchedBetaManagedAgentsSessionEvent =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to listOf("invalid"),
                            "processed_at" to listOf("invalid"),
                            "session_thread_id" to listOf("invalid"),
                            "tool_use_id" to listOf("invalid"),
                            "is_error" to listOf("invalid"),
                            "name" to listOf("invalid"),
                            "stop_details" to listOf("invalid"),
                            "agent_name" to listOf("invalid"),
                            "workflow_run_id" to listOf("invalid"),
                            "iteration" to listOf("invalid"),
                            "outcome_id" to listOf("invalid"),
                            "description" to listOf("invalid"),
                            "budget" to listOf("invalid"),
                            "workflow_run_phase_id" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaManagedAgentsSessionEvent>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaManagedAgentsSessionEvent.id() }
        assertThat(mismatchedBetaManagedAgentsSessionEvent.processedAt()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.sessionThreadId()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.toolUseId()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.isError()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.name()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.stopDetails()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.agentName()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.workflowRunId()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.iteration()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.outcomeId()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.description()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.budget()).isEmpty
        assertThat(mismatchedBetaManagedAgentsSessionEvent.workflowRunPhaseId()).isEmpty
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaManagedAgentsSessionEvent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsSessionEvent>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsSessionEvent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaManagedAgentsSessionEvent.id() }
        assertThat(betaManagedAgentsSessionEvent.processedAt()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.sessionThreadId()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.toolUseId()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.isError()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.name()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.evaluatedPermission()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.evaluation()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.stopDetails()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.agentName()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.workflowRunId()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.iteration()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.outcomeId()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.description()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.budget()).isEmpty
        assertThat(betaManagedAgentsSessionEvent.workflowRunPhaseId()).isEmpty
    }

    private fun presentVariants(
        betaManagedAgentsSessionEvent: BetaManagedAgentsSessionEvent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "userMessage" to betaManagedAgentsSessionEvent.userMessage(),
                "userInterrupt" to betaManagedAgentsSessionEvent.userInterrupt(),
                "userToolConfirmation" to betaManagedAgentsSessionEvent.userToolConfirmation(),
                "userCustomToolResult" to betaManagedAgentsSessionEvent.userCustomToolResult(),
                "agentCustomToolUse" to betaManagedAgentsSessionEvent.agentCustomToolUse(),
                "agentMessage" to betaManagedAgentsSessionEvent.agentMessage(),
                "agentThinking" to betaManagedAgentsSessionEvent.agentThinking(),
                "agentMcpToolUse" to betaManagedAgentsSessionEvent.agentMcpToolUse(),
                "agentMcpToolResult" to betaManagedAgentsSessionEvent.agentMcpToolResult(),
                "agentToolUse" to betaManagedAgentsSessionEvent.agentToolUse(),
                "agentToolResult" to betaManagedAgentsSessionEvent.agentToolResult(),
                "agentThreadMessageReceived" to
                    betaManagedAgentsSessionEvent.agentThreadMessageReceived(),
                "agentThreadMessageSent" to betaManagedAgentsSessionEvent.agentThreadMessageSent(),
                "agentThreadContextCompacted" to
                    betaManagedAgentsSessionEvent.agentThreadContextCompacted(),
                "sessionError" to betaManagedAgentsSessionEvent.sessionError(),
                "sessionStatusRescheduled" to
                    betaManagedAgentsSessionEvent.sessionStatusRescheduled(),
                "sessionStatusRunning" to betaManagedAgentsSessionEvent.sessionStatusRunning(),
                "sessionStatusIdle" to betaManagedAgentsSessionEvent.sessionStatusIdle(),
                "sessionStatusTerminated" to
                    betaManagedAgentsSessionEvent.sessionStatusTerminated(),
                "sessionThreadCreated" to betaManagedAgentsSessionEvent.sessionThreadCreated(),
                "spanOutcomeEvaluationStart" to
                    betaManagedAgentsSessionEvent.spanOutcomeEvaluationStart(),
                "spanOutcomeEvaluationEnd" to
                    betaManagedAgentsSessionEvent.spanOutcomeEvaluationEnd(),
                "spanModelRequestStart" to betaManagedAgentsSessionEvent.spanModelRequestStart(),
                "spanModelRequestEnd" to betaManagedAgentsSessionEvent.spanModelRequestEnd(),
                "spanOutcomeEvaluationOngoing" to
                    betaManagedAgentsSessionEvent.spanOutcomeEvaluationOngoing(),
                "userDefineOutcome" to betaManagedAgentsSessionEvent.userDefineOutcome(),
                "sessionDeleted" to betaManagedAgentsSessionEvent.sessionDeleted(),
                "sessionThreadStatusRunning" to
                    betaManagedAgentsSessionEvent.sessionThreadStatusRunning(),
                "sessionThreadStatusIdle" to
                    betaManagedAgentsSessionEvent.sessionThreadStatusIdle(),
                "sessionThreadStatusTerminated" to
                    betaManagedAgentsSessionEvent.sessionThreadStatusTerminated(),
                "userToolResult" to betaManagedAgentsSessionEvent.userToolResult(),
                "sessionThreadStatusRescheduled" to
                    betaManagedAgentsSessionEvent.sessionThreadStatusRescheduled(),
                "sessionUpdated" to betaManagedAgentsSessionEvent.sessionUpdated(),
                "systemMessage" to betaManagedAgentsSessionEvent.systemMessage(),
                "sessionUsage" to betaManagedAgentsSessionEvent.sessionUsage(),
                "workflowRunCreated" to betaManagedAgentsSessionEvent.workflowRunCreated(),
                "workflowRunStatusEnded" to betaManagedAgentsSessionEvent.workflowRunStatusEnded(),
                "workflowRunPhaseStarted" to
                    betaManagedAgentsSessionEvent.workflowRunPhaseStarted(),
                "workflowRunPhaseEnded" to betaManagedAgentsSessionEvent.workflowRunPhaseEnded(),
                "workflowRunStatusRunning" to
                    betaManagedAgentsSessionEvent.workflowRunStatusRunning(),
                "workflowRunStatusIdle" to betaManagedAgentsSessionEvent.workflowRunStatusIdle(),
                "workflowRunError" to betaManagedAgentsSessionEvent.workflowRunError(),
            )
            .filterValues { it.isPresent }
}
