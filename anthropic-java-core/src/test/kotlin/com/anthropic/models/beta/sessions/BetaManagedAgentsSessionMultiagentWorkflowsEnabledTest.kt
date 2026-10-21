package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
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
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgents
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsEnabled
import com.anthropic.models.beta.agents.BetaManagedAgentsServiceAccountExecutionIdentity
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionMultiagentWorkflowsEnabledTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionMultiagentWorkflowsEnabled =
            BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsSessionThreadAgent.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .description("A focused research subagent.")
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
                )
                .build()

        assertThat(betaManagedAgentsSessionMultiagentWorkflowsEnabled.inlineAgents())
            .isEqualTo(
                BetaManagedAgentsMultiagentInlineAgents.ofEnabled(
                    BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                )
            )
        assertThat(betaManagedAgentsSessionMultiagentWorkflowsEnabled.predefinedAgents())
            .containsExactly(
                BetaManagedAgentsSessionThreadAgent.builder()
                    .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                    .description("A focused research subagent.")
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
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagentWorkflowsEnabled =
            BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                .inlineAgents(BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build())
                .addPredefinedAgent(
                    BetaManagedAgentsSessionThreadAgent.builder()
                        .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                        .description("A focused research subagent.")
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
                )
                .build()

        val roundtrippedBetaManagedAgentsSessionMultiagentWorkflowsEnabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagentWorkflowsEnabled),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagentWorkflowsEnabled>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagentWorkflowsEnabled)
            .isEqualTo(betaManagedAgentsSessionMultiagentWorkflowsEnabled)
    }
}
