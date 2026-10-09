package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentInlineAgentsParamsTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaManagedAgentsMultiagentInlineAgentsEnabledParams.builder().build()

        val betaManagedAgentsMultiagentInlineAgentsParams =
            BetaManagedAgentsMultiagentInlineAgentsParams.ofEnabled(enabled)

        assertThat(betaManagedAgentsMultiagentInlineAgentsParams.enabled()).contains(enabled)
        assertThat(betaManagedAgentsMultiagentInlineAgentsParams.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgentsParams =
            BetaManagedAgentsMultiagentInlineAgentsParams.ofEnabled(
                BetaManagedAgentsMultiagentInlineAgentsEnabledParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentInlineAgentsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgentsParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgentsParams)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgentsParams)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()

        val betaManagedAgentsMultiagentInlineAgentsParams =
            BetaManagedAgentsMultiagentInlineAgentsParams.ofDisabled(disabled)

        assertThat(betaManagedAgentsMultiagentInlineAgentsParams.enabled()).isEmpty
        assertThat(betaManagedAgentsMultiagentInlineAgentsParams.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgentsParams =
            BetaManagedAgentsMultiagentInlineAgentsParams.ofDisabled(
                BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentInlineAgentsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgentsParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgentsParams)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgentsParams)
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
        val betaManagedAgentsMultiagentInlineAgentsParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgentsParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentInlineAgentsParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
