package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagent20261001
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentAdvisorDisabled
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentCoordinator
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsEnabled
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSubagentsEnabled
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentWorkflowsEnabled
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsMultiagentTest {

    @Test
    fun ofCoordinator() {
        val coordinator =
            BetaManagedAgentsMultiagentCoordinator.builder()
                .addAgent(
                    BetaManagedAgentsAgentReference.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .version(1)
                        .build()
                )
                .build()

        val betaManagedAgentsMultiagent = BetaManagedAgentsMultiagent.ofCoordinator(coordinator)

        assertThat(presentVariants(betaManagedAgentsMultiagent))
            .isEqualTo(mapOf("coordinator" to Optional.of(coordinator)))
    }

    @Test
    fun ofCoordinatorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagent =
            BetaManagedAgentsMultiagent.ofCoordinator(
                BetaManagedAgentsMultiagentCoordinator.builder()
                    .addAgent(
                        BetaManagedAgentsAgentReference.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .version(1)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagent),
                jacksonTypeRef<BetaManagedAgentsMultiagent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagent).isEqualTo(betaManagedAgentsMultiagent)
    }

    @Test
    fun ofMultiagent20261001() {
        val multiagent20261001 =
            BetaManagedAgentsMultiagent20261001.builder()
                .advisor(BetaManagedAgentsMultiagentAdvisorDisabled.builder().build())
                .subagents(
                    BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsAgentReference.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .version(1)
                                .build()
                        )
                        .build()
                )
                .workflows(
                    BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .predefinedAgents(listOf())
                        .build()
                )
                .build()

        val betaManagedAgentsMultiagent =
            BetaManagedAgentsMultiagent.ofMultiagent20261001(multiagent20261001)

        assertThat(presentVariants(betaManagedAgentsMultiagent))
            .isEqualTo(mapOf("multiagent20261001" to Optional.of(multiagent20261001)))
    }

    @Test
    fun ofMultiagent20261001Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagent =
            BetaManagedAgentsMultiagent.ofMultiagent20261001(
                BetaManagedAgentsMultiagent20261001.builder()
                    .advisor(BetaManagedAgentsMultiagentAdvisorDisabled.builder().build())
                    .subagents(
                        BetaManagedAgentsMultiagentSubagentsEnabled.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                            )
                            .addPredefinedAgent(
                                BetaManagedAgentsAgentReference.builder()
                                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                    .version(1)
                                    .build()
                            )
                            .build()
                    )
                    .workflows(
                        BetaManagedAgentsMultiagentWorkflowsEnabled.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                            )
                            .predefinedAgents(listOf())
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsMultiagent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagent),
                jacksonTypeRef<BetaManagedAgentsMultiagent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagent).isEqualTo(betaManagedAgentsMultiagent)
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
        val betaManagedAgentsMultiagent =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsMultiagent>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaManagedAgentsMultiagent.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaManagedAgentsMultiagent: BetaManagedAgentsMultiagent
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "coordinator" to betaManagedAgentsMultiagent.coordinator(),
                "multiagent20261001" to betaManagedAgentsMultiagent.multiagent20261001(),
            )
            .filterValues { it.isPresent }
}
