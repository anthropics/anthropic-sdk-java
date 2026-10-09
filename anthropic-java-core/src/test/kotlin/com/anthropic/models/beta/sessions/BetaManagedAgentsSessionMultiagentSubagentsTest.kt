package com.anthropic.models.beta.sessions

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsBashToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsEnabled
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentSubagentsDisabled
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsSessionMultiagentSubagentsTest {

    @Test
    fun ofEnabled() {
        val enabled =
            BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsSessionThreadAgent.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .description("A focused research subagent.")
                        .addMcpServer(
                            BetaManagedAgentsMcpServerUrlDefinition.builder()
                                .name("example-mcp")
                                .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                .url("https://example-server.modelcontextprotocol.io/sse")
                                .build()
                        )
                        .model(
                            BetaManagedAgentsModelConfig.builder()
                                .id(BetaManagedAgentsModel.CLAUDE_OPUS_5)
                                .effort(
                                    BetaManagedAgentsEffortLow.of(
                                        BetaManagedAgentsEffortLow.Type.LOW
                                    )
                                )
                                .inferenceGeo("inference_geo")
                                .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                .build()
                        )
                        .name("Researcher")
                        .addSkill(
                            BetaManagedAgentsAnthropicSkill.builder()
                                .skillId("xlsx")
                                .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                .version("1")
                                .build()
                        )
                        .system(
                            "You are a research subagent that gathers and summarises sources for the coordinating agent."
                        )
                        .addTool(
                            BetaManagedAgentsAgentToolset20260401.builder()
                                .addConfig(
                                    BetaManagedAgentsBashToolConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAllowPolicy.of(
                                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                            )
                                        )
                                        .build()
                                )
                                .defaultConfig(
                                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAskPolicy.of(
                                                BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK
                                            )
                                        )
                                        .build()
                                )
                                .type(
                                    BetaManagedAgentsAgentToolset20260401.Type
                                        .AGENT_TOOLSET_20260401
                                )
                                .build()
                        )
                        .type(BetaManagedAgentsSessionThreadAgent.Type.AGENT)
                        .version(1)
                        .build()
                )
                .build()

        val betaManagedAgentsSessionMultiagentSubagents =
            BetaManagedAgentsSessionMultiagentSubagents.ofEnabled(enabled)

        assertThat(betaManagedAgentsSessionMultiagentSubagents.enabled()).contains(enabled)
        assertThat(betaManagedAgentsSessionMultiagentSubagents.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagentSubagents =
            BetaManagedAgentsSessionMultiagentSubagents.ofEnabled(
                BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                    .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build())
                    .addPredefinedAgent(
                        BetaManagedAgentsSessionThreadAgent.builder()
                            .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                            .description("A focused research subagent.")
                            .addMcpServer(
                                BetaManagedAgentsMcpServerUrlDefinition.builder()
                                    .name("example-mcp")
                                    .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                    .url("https://example-server.modelcontextprotocol.io/sse")
                                    .build()
                            )
                            .model(
                                BetaManagedAgentsModelConfig.builder()
                                    .id(BetaManagedAgentsModel.CLAUDE_OPUS_5)
                                    .effort(
                                        BetaManagedAgentsEffortLow.of(
                                            BetaManagedAgentsEffortLow.Type.LOW
                                        )
                                    )
                                    .inferenceGeo("inference_geo")
                                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                    .build()
                            )
                            .name("Researcher")
                            .addSkill(
                                BetaManagedAgentsAnthropicSkill.builder()
                                    .skillId("xlsx")
                                    .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                                    .version("1")
                                    .build()
                            )
                            .system(
                                "You are a research subagent that gathers and summarises sources for the coordinating agent."
                            )
                            .addTool(
                                BetaManagedAgentsAgentToolset20260401.builder()
                                    .addConfig(
                                        BetaManagedAgentsBashToolConfig.builder()
                                            .enabled(true)
                                            .permissionPolicy(
                                                BetaManagedAgentsAlwaysAllowPolicy.of(
                                                    BetaManagedAgentsAlwaysAllowPolicy.Type
                                                        .ALWAYS_ALLOW
                                                )
                                            )
                                            .build()
                                    )
                                    .defaultConfig(
                                        BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                            .enabled(true)
                                            .permissionPolicy(
                                                BetaManagedAgentsAlwaysAskPolicy.of(
                                                    BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK
                                                )
                                            )
                                            .build()
                                    )
                                    .type(
                                        BetaManagedAgentsAgentToolset20260401.Type
                                            .AGENT_TOOLSET_20260401
                                    )
                                    .build()
                            )
                            .type(BetaManagedAgentsSessionThreadAgent.Type.AGENT)
                            .version(1)
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionMultiagentSubagents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagentSubagents),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagentSubagents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagentSubagents)
            .isEqualTo(betaManagedAgentsSessionMultiagentSubagents)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaManagedAgentsMultiagentSubagentsDisabled.builder().build()

        val betaManagedAgentsSessionMultiagentSubagents =
            BetaManagedAgentsSessionMultiagentSubagents.ofDisabled(disabled)

        assertThat(betaManagedAgentsSessionMultiagentSubagents.enabled()).isEmpty
        assertThat(betaManagedAgentsSessionMultiagentSubagents.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagentSubagents =
            BetaManagedAgentsSessionMultiagentSubagents.ofDisabled(
                BetaManagedAgentsMultiagentSubagentsDisabled.builder().build()
            )

        val roundtrippedBetaManagedAgentsSessionMultiagentSubagents =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagentSubagents),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagentSubagents>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagentSubagents)
            .isEqualTo(betaManagedAgentsSessionMultiagentSubagents)
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
        val betaManagedAgentsSessionMultiagentSubagents =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsSessionMultiagentSubagents>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsSessionMultiagentSubagents.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
