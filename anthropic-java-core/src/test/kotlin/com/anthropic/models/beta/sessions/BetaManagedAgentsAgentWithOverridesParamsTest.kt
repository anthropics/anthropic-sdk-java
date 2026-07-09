// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsUrlMcpServerParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentWithOverridesParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentWithOverridesParams =
            BetaManagedAgentsAgentWithOverridesParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentWithOverridesParams.Type.AGENT_WITH_OVERRIDES)
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_8)
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .system("system")
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .version(0)
                .build()

        assertThat(betaManagedAgentsAgentWithOverridesParams.id()).isEqualTo("x")
        assertThat(betaManagedAgentsAgentWithOverridesParams.type())
            .isEqualTo(BetaManagedAgentsAgentWithOverridesParams.Type.AGENT_WITH_OVERRIDES)
        assertThat(betaManagedAgentsAgentWithOverridesParams.mcpServers().getOrNull())
            .containsExactly(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.model())
            .contains(
                BetaManagedAgentsAgentWithOverridesParams.Model
                    .ofBetaManagedAgentsModelConfigParams(
                        BetaManagedAgentsModelConfigParams.builder()
                            .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_8)
                            .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                            .build()
                    )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.skills().getOrNull())
            .containsExactly(
                BetaManagedAgentsSkillParams.ofAnthropic(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.system()).contains("system")
        assertThat(betaManagedAgentsAgentWithOverridesParams.tools().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentWithOverridesParams.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.version()).contains(0)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsAgentWithOverridesParams =
            BetaManagedAgentsAgentWithOverridesParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentWithOverridesParams.Type.AGENT_WITH_OVERRIDES)
                .build()

        val betaManagedAgentsAgentWithOverridesParams =
            baseBetaManagedAgentsAgentWithOverridesParams
                .toBuilder()
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsSkillParams.ofAnthropic(
                        BetaManagedAgentsAnthropicSkillParams.builder()
                            .skillId("xlsx")
                            .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                            .version("1")
                            .build()
                    )
                )
                .addTool(
                    BetaManagedAgentsAgentWithOverridesParams.Tool.ofAgentToolset20260401(
                        BetaManagedAgentsAgentToolset20260401Params.builder()
                            .type(
                                BetaManagedAgentsAgentToolset20260401Params.Type
                                    .AGENT_TOOLSET_20260401
                            )
                            .addConfig(
                                BetaManagedAgentsAgentToolConfigParams.builder()
                                    .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                    .enabled(true)
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
                                BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                                            .type(
                                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                                            )
                                            .build()
                                    )
                                    .build()
                            )
                            .build()
                    )
                )
                .build()

        assertThat(betaManagedAgentsAgentWithOverridesParams.mcpServers().getOrNull())
            .containsExactly(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.skills().getOrNull())
            .containsExactly(
                BetaManagedAgentsSkillParams.ofAnthropic(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.tools().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentWithOverridesParams.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentWithOverridesParams =
            BetaManagedAgentsAgentWithOverridesParams.builder()
                .id("x")
                .type(BetaManagedAgentsAgentWithOverridesParams.Type.AGENT_WITH_OVERRIDES)
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_4_8)
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .version("1")
                        .build()
                )
                .system("system")
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .type(
                            BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401
                        )
                        .addConfig(
                            BetaManagedAgentsAgentToolConfigParams.builder()
                                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .version(0)
                .build()

        val roundtrippedBetaManagedAgentsAgentWithOverridesParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentWithOverridesParams),
                jacksonTypeRef<BetaManagedAgentsAgentWithOverridesParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentWithOverridesParams)
            .isEqualTo(betaManagedAgentsAgentWithOverridesParams)
    }
}
