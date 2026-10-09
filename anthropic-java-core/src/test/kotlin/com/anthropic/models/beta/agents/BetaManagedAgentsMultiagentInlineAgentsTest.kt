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

internal class BetaManagedAgentsMultiagentInlineAgentsTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()

        val betaManagedAgentsMultiagentInlineAgents =
            BetaManagedAgentsMultiagentInlineAgents.ofEnabled(enabled)

        assertThat(betaManagedAgentsMultiagentInlineAgents.enabled()).contains(enabled)
        assertThat(betaManagedAgentsMultiagentInlineAgents.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgents =
            BetaManagedAgentsMultiagentInlineAgents.ofEnabled(
                BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentInlineAgents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgents),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgents)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgents)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build()

        val betaManagedAgentsMultiagentInlineAgents =
            BetaManagedAgentsMultiagentInlineAgents.ofDisabled(disabled)

        assertThat(betaManagedAgentsMultiagentInlineAgents.enabled()).isEmpty
        assertThat(betaManagedAgentsMultiagentInlineAgents.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentInlineAgents =
            BetaManagedAgentsMultiagentInlineAgents.ofDisabled(
                BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentInlineAgents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentInlineAgents),
                jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentInlineAgents)
            .isEqualTo(betaManagedAgentsMultiagentInlineAgents)
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
        val betaManagedAgentsMultiagentInlineAgents =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentInlineAgents>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentInlineAgents.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
