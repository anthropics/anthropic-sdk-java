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

internal class BetaManagedAgentsMultiagentSubagentsTest {

    @Test
    fun ofEnabled() {
        val enabled =
            BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        val betaManagedAgentsMultiagentSubagents =
            BetaManagedAgentsMultiagentSubagents.ofEnabled(enabled)

        assertThat(betaManagedAgentsMultiagentSubagents.enabled()).contains(enabled)
        assertThat(betaManagedAgentsMultiagentSubagents.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagents =
            BetaManagedAgentsMultiagentSubagents.ofEnabled(
                BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                    .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                    .addPredefinedAgent(
                        BetaManagedAgentsAgentReference.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .type(BetaManagedAgentsAgentReference.Type.AGENT)
                            .version(1)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagentSubagents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagents),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagents)
            .isEqualTo(betaManagedAgentsMultiagentSubagents)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentSubagentsDisabled.builder().build()

        val betaManagedAgentsMultiagentSubagents =
            BetaManagedAgentsMultiagentSubagents.ofDisabled(disabled)

        assertThat(betaManagedAgentsMultiagentSubagents.enabled()).isEmpty
        assertThat(betaManagedAgentsMultiagentSubagents.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSubagents =
            BetaManagedAgentsMultiagentSubagents.ofDisabled(
                BetaManagedAgentsMultiagentSubagentsDisabled.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentSubagents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSubagents),
                jacksonTypeRef<BetaManagedAgentsMultiagentSubagents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSubagents)
            .isEqualTo(betaManagedAgentsMultiagentSubagents)
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
        val betaManagedAgentsMultiagentSubagents =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentSubagents>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentSubagents.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
