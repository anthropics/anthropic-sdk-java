// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpToolsetTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpToolset =
            BetaManagedAgentsMcpToolset.builder()
                .addConfig(
                    BetaManagedAgentsMcpToolConfig.builder()
                        .enabled(true)
                        .name("name")
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                .build()
                        )
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsMcpToolsetDefaultConfig.builder()
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                .build()
                        )
                        .build()
                )
                .mcpServerName("mcp_server_name")
                .type(BetaManagedAgentsMcpToolset.Type.MCP_TOOLSET)
                .build()

        assertThat(betaManagedAgentsMcpToolset.configs())
            .containsExactly(
                BetaManagedAgentsMcpToolConfig.builder()
                    .enabled(true)
                    .name("name")
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                            .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                            .build()
                    )
                    .build()
            )
        assertThat(betaManagedAgentsMcpToolset.defaultConfig())
            .isEqualTo(
                BetaManagedAgentsMcpToolsetDefaultConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                            .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                            .build()
                    )
                    .build()
            )
        assertThat(betaManagedAgentsMcpToolset.mcpServerName()).isEqualTo("mcp_server_name")
        assertThat(betaManagedAgentsMcpToolset.type())
            .isEqualTo(BetaManagedAgentsMcpToolset.Type.MCP_TOOLSET)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolset =
            BetaManagedAgentsMcpToolset.builder()
                .addConfig(
                    BetaManagedAgentsMcpToolConfig.builder()
                        .enabled(true)
                        .name("name")
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                .build()
                        )
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsMcpToolsetDefaultConfig.builder()
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                .build()
                        )
                        .build()
                )
                .mcpServerName("mcp_server_name")
                .type(BetaManagedAgentsMcpToolset.Type.MCP_TOOLSET)
                .build()

        val roundtrippedBetaManagedAgentsMcpToolset =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpToolset),
                jacksonTypeRef<BetaManagedAgentsMcpToolset>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpToolset).isEqualTo(betaManagedAgentsMcpToolset)
    }
}
