// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsSessionThreadAgent
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionMultiagentCoordinatorTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionMultiagentCoordinator =
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
                                .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
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
                                    BetaManagedAgentsAgentToolConfig.builder()
                                        .enabled(true)
                                        .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                .type(
                                                    BetaManagedAgentsAlwaysAllowPolicy.Type
                                                        .ALWAYS_ALLOW
                                                )
                                                .build()
                                        )
                                        .build()
                                )
                                .defaultConfig(
                                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAskPolicy.builder()
                                                .type(
                                                    BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK
                                                )
                                                .build()
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

        assertThat(betaManagedAgentsSessionMultiagentCoordinator.agents())
            .containsExactly(
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
                            .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
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
                                BetaManagedAgentsAgentToolConfig.builder()
                                    .enabled(true)
                                    .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                                            .type(
                                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                            )
                                            .build()
                                    )
                                    .build()
                            )
                            .defaultConfig(
                                BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAskPolicy.builder()
                                            .type(BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK)
                                            .build()
                                    )
                                    .build()
                            )
                            .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
                            .build()
                    )
                    .type(BetaManagedAgentsSessionThreadAgent.Type.AGENT)
                    .version(1)
                    .build()
            )
        assertThat(betaManagedAgentsSessionMultiagentCoordinator.type())
            .isEqualTo(BetaManagedAgentsSessionMultiagentCoordinator.Type.COORDINATOR)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionMultiagentCoordinator =
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
                                .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
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
                                    BetaManagedAgentsAgentToolConfig.builder()
                                        .enabled(true)
                                        .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                                .type(
                                                    BetaManagedAgentsAlwaysAllowPolicy.Type
                                                        .ALWAYS_ALLOW
                                                )
                                                .build()
                                        )
                                        .build()
                                )
                                .defaultConfig(
                                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                                        .enabled(true)
                                        .permissionPolicy(
                                            BetaManagedAgentsAlwaysAskPolicy.builder()
                                                .type(
                                                    BetaManagedAgentsAlwaysAskPolicy.Type.ALWAYS_ASK
                                                )
                                                .build()
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

        val roundtrippedBetaManagedAgentsSessionMultiagentCoordinator =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionMultiagentCoordinator),
                jacksonTypeRef<BetaManagedAgentsSessionMultiagentCoordinator>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionMultiagentCoordinator)
            .isEqualTo(betaManagedAgentsSessionMultiagentCoordinator)
    }
}
