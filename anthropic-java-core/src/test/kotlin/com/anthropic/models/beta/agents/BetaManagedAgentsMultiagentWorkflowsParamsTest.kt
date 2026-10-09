package com.anthropic.models.beta.agents

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentWorkflowsParamsTest {

    @Test
    fun ofEnabled() {
        val enabled =
            BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                .inlineAgents(
                    BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                )
                .addPredefinedAgent(
                    BetaManagedAgentsAgentParams.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .type(BetaManagedAgentsAgentParams.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        val betaManagedAgentsMultiagentWorkflowsParams =
            BetaManagedAgentsMultiagentWorkflowsParams.ofEnabled(enabled)

        assertThat(betaManagedAgentsMultiagentWorkflowsParams.enabled()).contains(enabled)
        assertThat(betaManagedAgentsMultiagentWorkflowsParams.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflowsParams =
            BetaManagedAgentsMultiagentWorkflowsParams.ofEnabled(
                BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                    .inlineAgents(
                        BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                    )
                    .addPredefinedAgent(
                        BetaManagedAgentsAgentParams.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .type(BetaManagedAgentsAgentParams.Type.AGENT)
                            .version(1)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagentWorkflowsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflowsParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflowsParams)
            .isEqualTo(betaManagedAgentsMultiagentWorkflowsParams)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentWorkflowsDisabledParams.builder().build()

        val betaManagedAgentsMultiagentWorkflowsParams =
            BetaManagedAgentsMultiagentWorkflowsParams.ofDisabled(disabled)

        assertThat(betaManagedAgentsMultiagentWorkflowsParams.enabled()).isEmpty
        assertThat(betaManagedAgentsMultiagentWorkflowsParams.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentWorkflowsParams =
            BetaManagedAgentsMultiagentWorkflowsParams.ofDisabled(
                BetaManagedAgentsMultiagentWorkflowsDisabledParams.builder().build()
            )

        val roundtrippedBetaManagedAgentsMultiagentWorkflowsParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentWorkflowsParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentWorkflowsParams)
            .isEqualTo(betaManagedAgentsMultiagentWorkflowsParams)
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
        val betaManagedAgentsMultiagentWorkflowsParams =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsMultiagentWorkflowsParams>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentWorkflowsParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
