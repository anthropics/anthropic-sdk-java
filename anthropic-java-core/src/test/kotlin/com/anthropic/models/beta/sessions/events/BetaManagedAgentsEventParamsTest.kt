package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsEventParamsTest {

    @Test
    fun ofUserMessage() {
        val userMessage =
            BetaManagedAgentsUserMessageEventParams.builder()
                .addTextContent("Where is my order #1234?")
                .build()

        val betaManagedAgentsEventParams = BetaManagedAgentsEventParams.ofUserMessage(userMessage)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("userMessage" to Optional.of(userMessage)))
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserMessage(
                BetaManagedAgentsUserMessageEventParams.builder()
                    .addTextContent("Where is my order #1234?")
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun ofUserInterrupt() {
        val userInterrupt =
            BetaManagedAgentsUserInterruptEventParams.builder()
                .sessionThreadId("session_thread_id")
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserInterrupt(userInterrupt)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("userInterrupt" to Optional.of(userInterrupt)))
    }

    @Test
    fun ofUserInterruptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserInterrupt(
                BetaManagedAgentsUserInterruptEventParams.builder()
                    .sessionThreadId("session_thread_id")
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun ofUserToolConfirmation() {
        val userToolConfirmation =
            BetaManagedAgentsUserToolConfirmationEventParams.builder()
                .result(BetaManagedAgentsUserToolConfirmationEventParams.Result.ALLOW)
                .toolUseId("x")
                .denyMessage("deny_message")
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserToolConfirmation(userToolConfirmation)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("userToolConfirmation" to Optional.of(userToolConfirmation)))
    }

    @Test
    fun ofUserToolConfirmationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserToolConfirmation(
                BetaManagedAgentsUserToolConfirmationEventParams.builder()
                    .result(BetaManagedAgentsUserToolConfirmationEventParams.Result.ALLOW)
                    .toolUseId("x")
                    .denyMessage("deny_message")
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun ofUserCustomToolResult() {
        val userCustomToolResult =
            BetaManagedAgentsUserCustomToolResultEventParams.builder()
                .customToolUseId("x")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserCustomToolResult(userCustomToolResult)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("userCustomToolResult" to Optional.of(userCustomToolResult)))
    }

    @Test
    fun ofUserCustomToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserCustomToolResult(
                BetaManagedAgentsUserCustomToolResultEventParams.builder()
                    .customToolUseId("x")
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun ofUserDefineOutcome() {
        val userDefineOutcome =
            BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                .description("Produce a 2-page summary as summary.md")
                .textRubric("Must cover all five sections; cite sources inline.")
                .maxIterations(3)
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserDefineOutcome(userDefineOutcome)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("userDefineOutcome" to Optional.of(userDefineOutcome)))
    }

    @Test
    fun ofUserDefineOutcomeRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserDefineOutcome(
                BetaManagedAgentsUserDefineOutcomeEventParams.builder()
                    .description("Produce a 2-page summary as summary.md")
                    .textRubric("Must cover all five sections; cite sources inline.")
                    .maxIterations(3)
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun ofUserToolResult() {
        val userToolResult =
            BetaManagedAgentsUserToolResultEventParams.builder()
                .toolUseId("x")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserToolResult(userToolResult)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("userToolResult" to Optional.of(userToolResult)))
    }

    @Test
    fun ofUserToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserToolResult(
                BetaManagedAgentsUserToolResultEventParams.builder()
                    .toolUseId("x")
                    .addTextContent("Where is my order #1234?")
                    .isError(true)
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun ofSystemMessage() {
        val systemMessage =
            BetaManagedAgentsSystemMessageEventParams.builder()
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofSystemMessage(systemMessage)

        assertThat(presentVariants(betaManagedAgentsEventParams))
            .isEqualTo(mapOf("systemMessage" to Optional.of(systemMessage)))
    }

    @Test
    fun ofSystemMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofSystemMessage(
                BetaManagedAgentsSystemMessageEventParams.builder()
                    .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                    .build()
            )

        val roundtrippedBetaManagedAgentsEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEventParams),
                jacksonTypeRef<BetaManagedAgentsEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEventParams).isEqualTo(betaManagedAgentsEventParams)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsEventParams =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "tool_use_id" to "x", "is_error" to true)
                    ),
                    jacksonTypeRef<BetaManagedAgentsEventParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsEventParams.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsEventParams.toolUseId()).contains("x")
        assertThat(betaManagedAgentsEventParams.isError()).contains(true)

        val mismatchedBetaManagedAgentsEventParams =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "tool_use_id" to listOf("invalid"),
                            "is_error" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaManagedAgentsEventParams>(),
                )

        assertThat(mismatchedBetaManagedAgentsEventParams.toolUseId()).isEmpty
        assertThat(mismatchedBetaManagedAgentsEventParams.isError()).isEmpty
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
        val betaManagedAgentsEventParams =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsEventParams>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsEventParams.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsEventParams.toolUseId()).isEmpty
        assertThat(betaManagedAgentsEventParams.isError()).isEmpty
    }

    private fun presentVariants(
        betaManagedAgentsEventParams: BetaManagedAgentsEventParams
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "userMessage" to betaManagedAgentsEventParams.userMessage(),
                "userInterrupt" to betaManagedAgentsEventParams.userInterrupt(),
                "userToolConfirmation" to betaManagedAgentsEventParams.userToolConfirmation(),
                "userCustomToolResult" to betaManagedAgentsEventParams.userCustomToolResult(),
                "userDefineOutcome" to betaManagedAgentsEventParams.userDefineOutcome(),
                "userToolResult" to betaManagedAgentsEventParams.userToolResult(),
                "systemMessage" to betaManagedAgentsEventParams.systemMessage(),
            )
            .filterValues { it.isPresent }
}
