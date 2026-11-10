package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentAdvisor
import com.anthropic.models.beta.agents.BetaManagedAgentsMultiagentInlineAgentsEnabled
import com.anthropic.models.beta.agents.BetaManagedAgentsServiceAccountExecutionIdentity
import com.anthropic.models.beta.agents.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionMultiagent20261001Test {

    @Test
    fun create() {
        val betaManagedAgentsSessionMultiagent20261001 =
            BetaManagedAgentsSessionMultiagent20261001.builder()
                .enabledAdvisor("claude-opus-5-5")
                .subagents(
                    BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .mcpServers(listOf())
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .skills(listOf())
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .configs(listOf())
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
                .workflows(
                    BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .mcpServers(listOf())
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .skills(listOf())
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .configs(listOf())
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
                .build()

        assertThat(betaManagedAgentsSessionMultiagent20261001.advisor())
            .isEqualTo(BetaManagedAgentsMultiagentAdvisor.ofEnabled("claude-opus-5-5"))
        assertThat(betaManagedAgentsSessionMultiagent20261001.subagents())
            .isEqualTo(
                BetaManagedAgentsSessionMultiagentSubagents.ofEnabled(
                    BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .mcpServers(listOf())
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .skills(listOf())
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .configs(listOf())
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
        assertThat(betaManagedAgentsSessionMultiagent20261001.workflows())
            .isEqualTo(
                BetaManagedAgentsSessionMultiagentWorkflows.ofEnabled(
                    BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .mcpServers(listOf())
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .skills(listOf())
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .configs(listOf())
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
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagent20261001 =
            BetaManagedAgentsSessionMultiagent20261001.builder()
                .enabledAdvisor("claude-opus-5-5")
                .subagents(
                    BetaManagedAgentsSessionMultiagentSubagentsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .mcpServers(listOf())
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .skills(listOf())
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .configs(listOf())
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
                .workflows(
                    BetaManagedAgentsSessionMultiagentWorkflowsEnabled.builder()
                        .inlineAgents(
                            BetaManagedAgentsMultiagentInlineAgentsEnabled.builder().build()
                        )
                        .addPredefinedAgent(
                            BetaManagedAgentsSessionThreadAgent.builder()
                                .id("agent_011CZkYqphY8vELVzwCUpqiQ")
                                .description("A focused research subagent.")
                                .executionIdentity(
                                    BetaManagedAgentsServiceAccountExecutionIdentity.builder()
                                        .build()
                                )
                                .mcpServers(listOf())
                                .model(
                                    BetaManagedAgentsModelConfig.builder()
                                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                                        .effort(BetaManagedAgentsEffortLow.builder().build())
                                        .inferenceGeo("inference_geo")
                                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                                        .build()
                                )
                                .name("Researcher")
                                .skills(listOf())
                                .system(
                                    "You are a research subagent that gathers and summarises sources for the coordinating agent."
                                )
                                .addTool(
                                    BetaManagedAgentsAgentToolset20260401.builder()
                                        .configs(listOf())
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
                .build()

        val roundtrippedBetaManagedAgentsSessionMultiagent20261001 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagent20261001),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagent20261001>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagent20261001)
            .isEqualTo(betaManagedAgentsSessionMultiagent20261001)
    }
}
