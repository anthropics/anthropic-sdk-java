package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsBashToolConfig
import com.anthropic.models.beta.agents.BetaManagedAgentsEffortLow
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsInlineAgentTest {

    @Test
    fun create() {
        val betaManagedAgentsInlineAgent =
            BetaManagedAgentsInlineAgent.builder()
                .description(null)
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
                        .effort(BetaManagedAgentsEffortLow.of(BetaManagedAgentsEffortLow.Type.LOW))
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                        .build()
                )
                .name("pdf-reader-3")
                .addSkill(
                    BetaManagedAgentsAnthropicSkill.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .system("You extract tables precisely. Output CSV only.")
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
                                    BetaManagedAgentsAlwaysAllowPolicy.of(
                                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                    )
                                )
                                .build()
                        )
                        .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsInlineAgent.description()).isEmpty
        assertThat(betaManagedAgentsInlineAgent.mcpServers())
            .containsExactly(
                BetaManagedAgentsMcpServerUrlDefinition.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsMcpServerUrlDefinition.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsInlineAgent.model())
            .isEqualTo(
                BetaManagedAgentsModelConfig.builder()
                    .id(BetaManagedAgentsModel.CLAUDE_OPUS_5)
                    .effort(BetaManagedAgentsEffortLow.of(BetaManagedAgentsEffortLow.Type.LOW))
                    .inferenceGeo("inference_geo")
                    .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                    .build()
            )
        assertThat(betaManagedAgentsInlineAgent.name()).isEqualTo("pdf-reader-3")
        assertThat(betaManagedAgentsInlineAgent.skills())
            .containsExactly(
                BetaManagedAgentsInlineAgent.Skill.ofAnthropic(
                    BetaManagedAgentsAnthropicSkill.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
            )
        assertThat(betaManagedAgentsInlineAgent.system())
            .contains("You extract tables precisely. Output CSV only.")
        assertThat(betaManagedAgentsInlineAgent.tools())
            .containsExactly(
                BetaManagedAgentsInlineAgent.Tool.ofAgentToolset20260401(
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
                                    BetaManagedAgentsAlwaysAllowPolicy.of(
                                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                    )
                                )
                                .build()
                        )
                        .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsInlineAgent =
            BetaManagedAgentsInlineAgent.builder()
                .description(null)
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
                        .effort(BetaManagedAgentsEffortLow.of(BetaManagedAgentsEffortLow.Type.LOW))
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfig.Speed.STANDARD)
                        .build()
                )
                .name("pdf-reader-3")
                .addSkill(
                    BetaManagedAgentsAnthropicSkill.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkill.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .system("You extract tables precisely. Output CSV only.")
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
                                    BetaManagedAgentsAlwaysAllowPolicy.of(
                                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                    )
                                )
                                .build()
                        )
                        .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsInlineAgent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsInlineAgent),
                jacksonTypeRef<BetaManagedAgentsInlineAgent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsInlineAgent).isEqualTo(betaManagedAgentsInlineAgent)
    }
}
