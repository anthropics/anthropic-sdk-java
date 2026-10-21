package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsBashToolConfigParams
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
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                        .effort(
                            BetaManagedAgentsModelConfigParams.Effort.BetaManagedAgentsEffortLevel
                                .LOW
                        )
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
                .system("system")
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .build()
                        )
                        .build()
                )
                .version(0)
                .build()

        assertThat(betaManagedAgentsAgentWithOverridesParams.id()).isEqualTo("x")
        assertThat(betaManagedAgentsAgentWithOverridesParams.mcpServers().getOrNull())
            .containsExactly(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.model())
            .contains(
                BetaManagedAgentsAgentWithOverridesParams.Model
                    .ofBetaManagedAgentsModelConfigParams(
                        BetaManagedAgentsModelConfigParams.builder()
                            .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                            .effort(
                                BetaManagedAgentsModelConfigParams.Effort
                                    .BetaManagedAgentsEffortLevel
                                    .LOW
                            )
                            .inferenceGeo("inference_geo")
                            .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                            .build()
                    )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.skills().getOrNull())
            .containsExactly(
                BetaManagedAgentsSkillParams.ofAnthropic(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.system()).contains("system")
        assertThat(betaManagedAgentsAgentWithOverridesParams.tools().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentWithOverridesParams.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
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
            BetaManagedAgentsAgentWithOverridesParams.of("x")

        val betaManagedAgentsAgentWithOverridesParams =
            baseBetaManagedAgentsAgentWithOverridesParams
                .toBuilder()
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsSkillParams.ofAnthropic(
                        BetaManagedAgentsAnthropicSkillParams.builder()
                            .skillId("xlsx")
                            .version("1")
                            .build()
                    )
                )
                .addTool(
                    BetaManagedAgentsAgentWithOverridesParams.Tool.ofAgentToolset20260401(
                        BetaManagedAgentsAgentToolset20260401Params.builder()
                            .addConfig(
                                BetaManagedAgentsBashToolConfigParams.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                    )
                                    .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                    .build()
                            )
                            .defaultConfig(
                                BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                    .enabled(true)
                                    .permissionPolicy(
                                        BetaManagedAgentsAlwaysAllowPolicy.builder().build()
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
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.skills().getOrNull())
            .containsExactly(
                BetaManagedAgentsSkillParams.ofAnthropic(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentWithOverridesParams.tools().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentWithOverridesParams.Tool.ofAgentToolset20260401(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
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
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
                .model(
                    BetaManagedAgentsModelConfigParams.builder()
                        .id(BetaManagedAgentsModel.CLAUDE_OPUS_5_5)
                        .effort(
                            BetaManagedAgentsModelConfigParams.Effort.BetaManagedAgentsEffortLevel
                                .LOW
                        )
                        .inferenceGeo("inference_geo")
                        .speed(BetaManagedAgentsModelConfigParams.Speed.STANDARD)
                        .build()
                )
                .addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .skillId("xlsx")
                        .version("1")
                        .build()
                )
                .system("system")
                .addTool(
                    BetaManagedAgentsAgentToolset20260401Params.builder()
                        .addConfig(
                            BetaManagedAgentsBashToolConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                                )
                                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                                .build()
                        )
                        .defaultConfig(
                            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                                .enabled(true)
                                .permissionPolicy(
                                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
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
