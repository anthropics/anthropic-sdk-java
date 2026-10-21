package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsBashToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsExecutionIdentity
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsServiceAccountExecutionIdentity
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionAgentTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionAgent =
            BetaManagedAgentsSessionAgent.builder()
                .id("agent_011CZkYpogX7uDKUyvBTophP")
                .description("A general-purpose starter agent.")
                .executionIdentity(
                    BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                )
                .addMcpServer(
                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfig.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                        .effort(BetaManagedAgentsEffortLow.builder().build())
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                        .build()
                )
                .coordinatorMultiagent(
                    listOf(
                        BetaManagedAgentsSessionMultiagentCoordinator.Agent.ofAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .addMcpServer(
                                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                                        .name("example-mcp")
                                        .url("https://example-server.modelcontextprotocol.io/sse")
                                        .build()
                                )
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
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
                                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .defaultConfig(
                                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                                .enabled(true)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAskPolicy.builder()
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .build()
                                )
                                .version(1)
                                .build()
                        )
                    )
                )
                .name("My First Agent")
                .addSkill(
                    BetaManagedAgentsAnthropicSkill.builder().skillId("xlsx").version("1").build()
                )
                .addSkill(
                    BetaManagedAgentsCustomSkill.builder()
                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                        .version("2")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .version(1)
                .build()

        assertThat(betaManagedAgentsSessionAgent.id()).isEqualTo("agent_011CZkYpogX7uDKUyvBTophP")
        assertThat(betaManagedAgentsSessionAgent.description())
            .contains("A general-purpose starter agent.")
        assertThat(betaManagedAgentsSessionAgent.executionIdentity())
            .isEqualTo(
                BetaManagedAgentsExecutionIdentity.ofServiceAccount(
                    BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                )
            )
        assertThat(betaManagedAgentsSessionAgent.mcpServers())
            .containsExactly(
                BetaManagedAgentsMcpServerUrlDefinition.builder()
                    .name("example-mcp")
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsSessionAgent.model())
            .isEqualTo(
                BetaManagedAgentsModelConfig.builder()
                    .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                    .effort(BetaManagedAgentsEffortLow.builder().build())
                    .inferenceGeo("inference_geo")
                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                    .build()
            )
        assertThat(betaManagedAgentsSessionAgent.multiagent())
            .contains(
                BetaManagedAgentsSessionMultiagent.ofCoordinator(
                    BetaManagedAgentsSessionMultiagentCoordinator.builder()
                        .addAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .addMcpServer(
                                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                                        .name("example-mcp")
                                        .url("https://example-server.modelcontextprotocol.io/sse")
                                        .build()
                                )
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
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
                                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .defaultConfig(
                                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                                .enabled(true)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAskPolicy.builder()
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .build()
                                )
                                .version(1)
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsSessionAgent.name()).isEqualTo("My First Agent")
        assertThat(betaManagedAgentsSessionAgent.skills())
            .containsExactly(
                BetaManagedAgentsSessionAgent.Skill.ofAnthropic(
                    BetaManagedAgentsAnthropicSkill.builder().skillId("xlsx").version("1").build()
                ),
                BetaManagedAgentsSessionAgent.Skill.ofCustom(
                    BetaManagedAgentsCustomSkill.builder()
                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                        .version("2")
                        .build()
                ),
            )
        assertThat(betaManagedAgentsSessionAgent.system())
            .contains(
                "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
            )
        assertThat(betaManagedAgentsSessionAgent.tools())
            .containsExactly(
                BetaManagedAgentsSessionAgent.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsSessionAgent.version()).isEqualTo(1)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionAgent =
            BetaManagedAgentsSessionAgent.builder()
                .id("agent_011CZkYpogX7uDKUyvBTophP")
                .description("A general-purpose starter agent.")
                .executionIdentity(
                    BetaManagedAgentsServiceAccountExecutionIdentity.builder().build()
                )
                .addMcpServer(
                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfig.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                        .effort(BetaManagedAgentsEffortLow.builder().build())
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                        .build()
                )
                .coordinatorMultiagent(
                    listOf(
                        BetaManagedAgentsSessionMultiagentCoordinator.Agent.ofAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .addMcpServer(
                                    BetaManagedAgentsMcpServerUrlDefinition.builder()
                                        .name("example-mcp")
                                        .url("https://example-server.modelcontextprotocol.io/sse")
                                        .build()
                                )
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .addSkill(
                                    BetaManagedAgentsAnthropicSkill.builder()
                                        .skillId("xlsx")
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
                                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .defaultConfig(
                                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                                .enabled(true)
                                                .permissionPolicy(
                                                    BetaManagedAgentsAlwaysAskPolicy.builder()
                                                        .build()
                                                )
                                                .build()
                                        )
                                        .build()
                                )
                                .version(1)
                                .build()
                        )
                    )
                )
                .name("My First Agent")
                .addSkill(
                    BetaManagedAgentsAnthropicSkill.builder().skillId("xlsx").version("1").build()
                )
                .addSkill(
                    BetaManagedAgentsCustomSkill.builder()
                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTmx")
                        .version("2")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAskPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .version(1)
                .build()

        val roundtrippedBetaManagedAgentsSessionAgent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionAgent),
                jacksonTypeRef<BetaManagedAgentsSessionAgent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionAgent)
            .isEqualTo(betaManagedAgentsSessionAgent)
    }
}
