// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
                .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                .build()

        val betaManagedAgentsEventParams = BetaManagedAgentsEventParams.ofUserMessage(userMessage)

        assertThat(betaManagedAgentsEventParams.userMessage()).contains(userMessage)
        assertThat(betaManagedAgentsEventParams.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsEventParams.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsEventParams.userCustomToolResult()).isEmpty
    }

    @Test
    fun ofUserMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserMessage(
                BetaManagedAgentsUserMessageEventParams.builder()
                    .addTextContent("Where is my order #1234?")
                    .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
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
                .type(BetaManagedAgentsUserInterruptEventParams.Type.USER_INTERRUPT)
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserInterrupt(userInterrupt)

        assertThat(betaManagedAgentsEventParams.userMessage()).isEmpty
        assertThat(betaManagedAgentsEventParams.userInterrupt()).contains(userInterrupt)
        assertThat(betaManagedAgentsEventParams.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsEventParams.userCustomToolResult()).isEmpty
    }

    @Test
    fun ofUserInterruptRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserInterrupt(
                BetaManagedAgentsUserInterruptEventParams.builder()
                    .type(BetaManagedAgentsUserInterruptEventParams.Type.USER_INTERRUPT)
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
                .type(BetaManagedAgentsUserToolConfirmationEventParams.Type.USER_TOOL_CONFIRMATION)
                .denyMessage("deny_message")
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserToolConfirmation(userToolConfirmation)

        assertThat(betaManagedAgentsEventParams.userMessage()).isEmpty
        assertThat(betaManagedAgentsEventParams.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsEventParams.userToolConfirmation())
            .contains(userToolConfirmation)
        assertThat(betaManagedAgentsEventParams.userCustomToolResult()).isEmpty
    }

    @Test
    fun ofUserToolConfirmationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserToolConfirmation(
                BetaManagedAgentsUserToolConfirmationEventParams.builder()
                    .result(BetaManagedAgentsUserToolConfirmationEventParams.Result.ALLOW)
                    .toolUseId("x")
                    .type(
                        BetaManagedAgentsUserToolConfirmationEventParams.Type.USER_TOOL_CONFIRMATION
                    )
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
                .type(BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserCustomToolResult(userCustomToolResult)

        assertThat(betaManagedAgentsEventParams.userMessage()).isEmpty
        assertThat(betaManagedAgentsEventParams.userInterrupt()).isEmpty
        assertThat(betaManagedAgentsEventParams.userToolConfirmation()).isEmpty
        assertThat(betaManagedAgentsEventParams.userCustomToolResult())
            .contains(userCustomToolResult)
    }

    @Test
    fun ofUserCustomToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEventParams =
            BetaManagedAgentsEventParams.ofUserCustomToolResult(
                BetaManagedAgentsUserCustomToolResultEventParams.builder()
                    .customToolUseId("x")
                    .type(
                        BetaManagedAgentsUserCustomToolResultEventParams.Type
                            .USER_CUSTOM_TOOL_RESULT
                    )
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
    }
}
