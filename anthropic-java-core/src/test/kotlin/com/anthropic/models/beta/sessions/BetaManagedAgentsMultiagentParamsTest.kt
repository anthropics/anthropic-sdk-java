package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagent20261001Params
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentAdvisorDisabledParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentCoordinatorParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsDisabledParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSelfParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSubagentsEnabledParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentWorkflowsEnabledParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentParamsTest {

    @Test
    fun ofCoordinator() {
        val coordinator =
            BetaManagedAgentsMultiagentCoordinatorParams.builder()
                .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                .addAgent(
                    BetaManagedAgentsMultiagentSelfParams.of(
                        BetaManagedAgentsMultiagentSelfParams.Type.SELF
                    )
                )
                .type(BetaManagedAgentsMultiagentCoordinatorParams.Type.COORDINATOR)
                .build()

        val betaManagedAgentsMultiagentParams =
            BetaManagedAgentsMultiagentParams.ofCoordinator(coordinator)

        assertThat(betaManagedAgentsMultiagentParams.coordinator()).contains(coordinator)
        assertThat(betaManagedAgentsMultiagentParams.multiagent20261001()).isEmpty
    }

    @Test
    fun ofCoordinatorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentParams =
            BetaManagedAgentsMultiagentParams.ofCoordinator(
                BetaManagedAgentsMultiagentCoordinatorParams.builder()
                    .addAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .addAgent(
                        BetaManagedAgentsMultiagentSelfParams.of(
                            BetaManagedAgentsMultiagentSelfParams.Type.SELF
                        )
                    )
                    .type(BetaManagedAgentsMultiagentCoordinatorParams.Type.COORDINATOR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentParams)
            .isEqualTo(betaManagedAgentsMultiagentParams)
    }

    @Test
    fun ofMultiagent20261001() {
        val multiagent20261001 =
            BetaManagedAgentsMultiagent20261001Params.builder()
                .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                .subagents(
                    BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder().build()
                        )
                        .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .build()
                )
                .workflows(
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
                .build()

        val betaManagedAgentsMultiagentParams =
            BetaManagedAgentsMultiagentParams.ofMultiagent20261001(multiagent20261001)

        assertThat(betaManagedAgentsMultiagentParams.coordinator()).isEmpty
        assertThat(betaManagedAgentsMultiagentParams.multiagent20261001())
            .contains(multiagent20261001)
    }

    @Test
    fun ofMultiagent20261001Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentParams =
            BetaManagedAgentsMultiagentParams.ofMultiagent20261001(
                BetaManagedAgentsMultiagent20261001Params.builder()
                    .advisor(BetaManagedAgentsMultiagentAdvisorDisabledParams.builder().build())
                    .subagents(
                        BetaManagedAgentsMultiagentSubagentsEnabledParams.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                    .build()
                            )
                            .addPredefinedAgent("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .build()
                    )
                    .workflows(
                        BetaManagedAgentsMultiagentWorkflowsEnabledParams.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsDisabledParams.builder()
                                    .build()
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
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagentParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentParams)
            .isEqualTo(betaManagedAgentsMultiagentParams)
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
        val betaManagedAgentsMultiagentParams =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsMultiagentParams>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsMultiagentParams.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
