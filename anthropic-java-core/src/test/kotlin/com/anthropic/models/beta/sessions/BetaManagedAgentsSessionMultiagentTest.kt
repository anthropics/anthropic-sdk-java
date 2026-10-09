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
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsSessionMultiagentTest {

    @Test
    fun ofCoordinator() {
        val coordinator =
            BetaManagedAgentsSessionMultiagentCoordinator.builder()
                .addAgent(
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
                .type(BetaManagedAgentsSessionMultiagentCoordinator.Type.COORDINATOR)
                .build()

        val betaManagedAgentsSessionMultiagent =
            BetaManagedAgentsSessionMultiagent.ofCoordinator(coordinator)

        assertThat(betaManagedAgentsSessionMultiagent.coordinator()).contains(coordinator)
        assertThat(betaManagedAgentsSessionMultiagent.multiagent20261001()).isEmpty
    }

    @Test
    fun ofCoordinatorRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagent =
            BetaManagedAgentsSessionMultiagent.ofCoordinator(
                BetaManagedAgentsSessionMultiagentCoordinator.builder()
                    .addAgent(
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
                    .type(BetaManagedAgentsSessionMultiagentCoordinator.Type.COORDINATOR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionMultiagent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagent),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagent)
            .isEqualTo(betaManagedAgentsSessionMultiagent)
    }

    @Test
    fun ofMultiagent20261001() {
        val multiagent20261001 =
            BetaManagedAgentsSessionMultiagent20261001.builder()
                .enabledAdvisor("claude-fable-5")
                .subagents(
                    BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
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
                                                        BetaManagedAgentsAlwaysAskPolicy.Type
                                                            .ALWAYS_ASK
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
                .workflows(
                    BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
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
                                                        BetaManagedAgentsAlwaysAskPolicy.Type
                                                            .ALWAYS_ASK
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
                .build()

        val betaManagedAgentsSessionMultiagent =
            BetaManagedAgentsSessionMultiagent.ofMultiagent20261001(multiagent20261001)

        assertThat(betaManagedAgentsSessionMultiagent.coordinator()).isEmpty
        assertThat(betaManagedAgentsSessionMultiagent.multiagent20261001())
            .contains(multiagent20261001)
    }

    @Test
    fun ofMultiagent20261001Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagent =
            BetaManagedAgentsSessionMultiagent.ofMultiagent20261001(
                BetaManagedAgentsSessionMultiagent20261001.builder()
                    .enabledAdvisor("claude-fable-5")
                    .subagents(
                        BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                            )
                            .addPredefinedAgent(
                                BetaManagedAgentsSessionThreadAgent.builder()
                                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                    .description("A focused research subagent.")
                                    .addMcpServer(
                                        BetaManagedAgentsMcpServerUrlDefinition.builder()
                                            .name("example-mcp")
                                            .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                            .url(
                                                "https://example-server.modelcontextprotocol.io/sse"
                                            )
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
                                                            BetaManagedAgentsAlwaysAskPolicy.Type
                                                                .ALWAYS_ASK
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
                    .workflows(
                        BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                            .inlineAgents(
                                BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                            )
                            .addPredefinedAgent(
                                BetaManagedAgentsSessionThreadAgent.builder()
                                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                    .description("A focused research subagent.")
                                    .addMcpServer(
                                        BetaManagedAgentsMcpServerUrlDefinition.builder()
                                            .name("example-mcp")
                                            .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                                            .url(
                                                "https://example-server.modelcontextprotocol.io/sse"
                                            )
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
                                                            BetaManagedAgentsAlwaysAskPolicy.Type
                                                                .ALWAYS_ASK
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
                    .build()
            )

        val roundtrippedBetaManagedAgentsSessionMultiagent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagent),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagent)
            .isEqualTo(betaManagedAgentsSessionMultiagent)
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
        val betaManagedAgentsSessionMultiagent =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaManagedAgentsSessionMultiagent>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsSessionMultiagent.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
