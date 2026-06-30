// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsStartEventPreviewTest {

    @Test
    fun ofAgentMessage() {
        val agentMessage =
            BetaManagedAgentsAgentMessagePreview.builder()
                .id("id")
                .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                .build()

        val betaManagedAgentsStartEventPreview =
            BetaManagedAgentsStartEventPreview.ofAgentMessage(agentMessage)

        assertThat(betaManagedAgentsStartEventPreview.agentMessage()).contains(agentMessage)
        assertThat(betaManagedAgentsStartEventPreview.agentThinking()).isEmpty
    }

    @Test
    fun ofAgentMessageRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStartEventPreview =
            BetaManagedAgentsStartEventPreview.ofAgentMessage(
                BetaManagedAgentsAgentMessagePreview.builder()
                    .id("id")
                    .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStartEventPreview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStartEventPreview),
                jacksonTypeRef<BetaManagedAgentsStartEventPreview>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStartEventPreview)
            .isEqualTo(betaManagedAgentsStartEventPreview)
    }

    @Test
    fun ofAgentThinking() {
        val agentThinking =
            BetaManagedAgentsAgentThinkingPreview.builder()
                .id("id")
                .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
                .build()

        val betaManagedAgentsStartEventPreview =
            BetaManagedAgentsStartEventPreview.ofAgentThinking(agentThinking)

        assertThat(betaManagedAgentsStartEventPreview.agentMessage()).isEmpty
        assertThat(betaManagedAgentsStartEventPreview.agentThinking()).contains(agentThinking)
    }

    @Test
    fun ofAgentThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsStartEventPreview =
            BetaManagedAgentsStartEventPreview.ofAgentThinking(
                BetaManagedAgentsAgentThinkingPreview.builder()
                    .id("id")
                    .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
                    .build()
            )

        val roundtrippedBetaManagedAgentsStartEventPreview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsStartEventPreview),
                jacksonTypeRef<BetaManagedAgentsStartEventPreview>(),
            )

        assertThat(roundtrippedBetaManagedAgentsStartEventPreview)
            .isEqualTo(betaManagedAgentsStartEventPreview)
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
        val betaManagedAgentsStartEventPreview =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsStartEventPreview>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsStartEventPreview.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
