// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAskPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
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
                .name("My First Agent")
                .addSkill(
                    BetaManagedAgentsAnthropicSkill.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsCustomSkill.builder()
                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                        .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                        .version("2")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .addConfig(
                            BetaManagedAgentsAgentToolConfig.builder()
                                .enabled(true)
                                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
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
                .type(BetaManagedAgentsSessionAgent.Type.AGENT)
                .version(1)
                .build()

        assertThat(betaManagedAgentsSessionAgent.id()).isEqualTo("agent_011CZkYpogX7uDKUyvBTophP")
        assertThat(betaManagedAgentsSessionAgent.description())
            .contains("A general-purpose starter agent.")
        assertThat(betaManagedAgentsSessionAgent.mcpServers())
            .containsExactly(
                BetaManagedAgentsMcpServerUrlDefinition.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsSessionAgent.model())
            .isEqualTo(
                BetaManagedAgentsModelConfig.builder()
                    .id(BetaManagedAgentsModel.CLAUDE_SONNET_4_6)
                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                    .build()
            )
        assertThat(betaManagedAgentsSessionAgent.name()).isEqualTo("My First Agent")
        assertThat(betaManagedAgentsSessionAgent.skills())
            .containsExactly(
                BetaManagedAgentsSessionAgent.Skill.ofAnthropic(
                    BetaManagedAgentsAnthropicSkill.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                        .version("1")
                        .build()
                ),
                BetaManagedAgentsSessionAgent.Skill.ofCustom(
                    BetaManagedAgentsCustomSkill.builder()
                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                        .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
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
                            BetaManagedAgentsAgentToolConfig.builder()
                                .enabled(true)
                                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
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
            )
        assertThat(betaManagedAgentsSessionAgent.type())
            .isEqualTo(BetaManagedAgentsSessionAgent.Type.AGENT)
        assertThat(betaManagedAgentsSessionAgent.version()).isEqualTo(1)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSessionAgent =
            BetaManagedAgentsSessionAgent.builder()
                .id("agent_011CZkYpogX7uDKUyvBTophP")
                .description("A general-purpose starter agent.")
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
                .name("My First Agent")
                .addSkill(
                    BetaManagedAgentsAnthropicSkill.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsCustomSkill.builder()
                        .skillId("skill_011CZkZFNu9hAbo3jZPRgTlx")
                        .type(BetaManagedAgentsCustomSkill.Type.CUSTOM)
                        .version("2")
                        .build()
                )
                .system(
                    "You are a general-purpose agent that can research, write code, run commands, and use connected tools to complete the user's task end to end."
                )
                .addTool(
                    BetaManagedAgentsAgentToolset20260401.builder()
                        .addConfig(
                            BetaManagedAgentsAgentToolConfig.builder()
                                .enabled(true)
                                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
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
                .type(BetaManagedAgentsSessionAgent.Type.AGENT)
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
