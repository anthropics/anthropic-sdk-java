// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolsetDefaultConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsAlwaysAllowPolicy
import com.anthropic.models.beta.agents.BetaManagedAgentsUrlMcpServerParams
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSessionAgentUpdateTest {

    @Test
    fun create() {
        val betaManagedAgentsSessionAgentUpdate =
            BetaManagedAgentsSessionAgentUpdate.builder()
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
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
                .build()

        assertThat(betaManagedAgentsSessionAgentUpdate.mcpServers().getOrNull())
            .containsExactly(
                BetaManagedAgentsUrlMcpServerParams.builder()
                    .name("example-mcp")
                    .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                    .url("https://example-server.modelcontextprotocol.io/sse")
                    .build()
            )
        assertThat(betaManagedAgentsSessionAgentUpdate.tools().getOrNull())
            .containsExactly(
                BetaManagedAgentsSessionAgentUpdate.Tool.ofAgentToolset20260401(
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
        val betaManagedAgentsSessionAgentUpdate =
            BetaManagedAgentsSessionAgentUpdate.builder()
                .addMcpServer(
                    BetaManagedAgentsUrlMcpServerParams.builder()
                        .name("example-mcp")
                        .type(BetaManagedAgentsUrlMcpServerParams.Type.URL)
                        .url("https://example-server.modelcontextprotocol.io/sse")
                        .build()
                )
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
                .build()

        val roundtrippedBetaManagedAgentsSessionAgentUpdate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSessionAgentUpdate),
                jacksonTypeRef<BetaManagedAgentsSessionAgentUpdate>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSessionAgentUpdate)
            .isEqualTo(betaManagedAgentsSessionAgentUpdate)
    }
}
