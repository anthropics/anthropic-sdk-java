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

internal class BetaManagedAgentsMultiagentWorkflowsTest {

    @Test
    fun ofEnabled() {
        val enabled =
            BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsDisabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentReference.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        val betaManagedAgentsMultiagentWorkflows =
            BetaManagedAgentsMultiagentWorkflows.ofEnabled(enabled)

        assertThat(betaManagedAgentsMultiagentWorkflows.enabled()).contains(enabled)
        assertThat(betaManagedAgentsMultiagentWorkflows.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflows =
            BetaManagedAgentsMultiagentWorkflows.ofEnabled(
                BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
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

        val roundtrippedBetaManagedAgentsMultiagentWorkflows =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflows),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflows>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflows)
            .isEqualTo(betaManagedAgentsMultiagentWorkflows)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentWorkflowsDisabled.builder().build()

        val betaManagedAgentsMultiagentWorkflows =
            BetaManagedAgentsMultiagentWorkflows.ofDisabled(disabled)

        assertThat(betaManagedAgentsMultiagentWorkflows.enabled()).isEmpty
        assertThat(betaManagedAgentsMultiagentWorkflows.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflows =
            BetaManagedAgentsMultiagentWorkflows.ofDisabled(
                BetaManagedAgentsMultiagentWorkflowsDisabled.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentWorkflows =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflows),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflows>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflows)
            .isEqualTo(betaManagedAgentsMultiagentWorkflows)
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
        val betaManagedAgentsMultiagentWorkflows =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentWorkflows>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentWorkflows.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
