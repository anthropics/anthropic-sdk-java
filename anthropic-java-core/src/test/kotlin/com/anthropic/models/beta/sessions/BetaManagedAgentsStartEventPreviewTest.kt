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
        val agentMessage = BetaManagedAgentsAgentMessagePreview.of("id")

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
                BetaManagedAgentsAgentMessagePreview.of("id")
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
        val agentThinking = BetaManagedAgentsAgentThinkingPreview.of("id")

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
                BetaManagedAgentsAgentThinkingPreview.of("id")
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
    fun unknownVariantCommonProperties() {
        val betaManagedAgentsStartEventPreview =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "id" to "id")),
                    jacksonTypeRef<BetaManagedAgentsStartEventPreview>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsStartEventPreview.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaManagedAgentsStartEventPreview.id()).isEqualTo("id")

        val mismatchedBetaManagedAgentsStartEventPreview =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "id" to listOf("invalid"))),
                    jacksonTypeRef<BetaManagedAgentsStartEventPreview>(),
                )

        assertThrows<AnthropicInvalidDataException> {
            mismatchedBetaManagedAgentsStartEventPreview.id()
        }
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

        assertThrows<AnthropicInvalidDataException> { betaManagedAgentsStartEventPreview.id() }
    }
}
