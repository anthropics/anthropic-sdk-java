// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpToolsetParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpToolsetParams =
            BetaManagedAgentsMcpToolsetParams.builder()
                .mcpServerName("x")
                .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
                .addConfig(
                    BetaManagedAgentsMcpToolConfigParams.builder()
                        .name("x")
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.of(
                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                            )
                        )
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.of(
                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                            )
                        )
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolsetParams.mcpServerName()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpToolsetParams.type())
            .isEqualTo(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
        assertThat(betaManagedAgentsMcpToolsetParams.configs().getOrNull())
            .containsExactly(
                BetaManagedAgentsMcpToolConfigParams.builder()
                    .name("x")
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
                    .build()
            )
        assertThat(betaManagedAgentsMcpToolsetParams.defaultConfig())
            .contains(
                BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsMcpToolsetParams =
            BetaManagedAgentsMcpToolsetParams.builder()
                .mcpServerName("x")
                .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
                .build()

        val betaManagedAgentsMcpToolsetParams =
            baseBetaManagedAgentsMcpToolsetParams
                .toBuilder()
                .addConfig(
                    BetaManagedAgentsMcpToolConfigParams.builder()
                        .name("x")
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.of(
                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                            )
                        )
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolsetParams.configs().getOrNull())
            .containsExactly(
                BetaManagedAgentsMcpToolConfigParams.builder()
                    .name("x")
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.of(
                            BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                        )
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolsetParams =
            BetaManagedAgentsMcpToolsetParams.builder()
                .mcpServerName("x")
                .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
                .addConfig(
                    BetaManagedAgentsMcpToolConfigParams.builder()
                        .name("x")
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.of(
                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                            )
                        )
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(
                            BetaManagedAgentsAlwaysAllowPolicy.of(
                                BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                            )
                        )
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpToolsetParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpToolsetParams),
                jacksonTypeRef<BetaManagedAgentsMcpToolsetParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpToolsetParams)
            .isEqualTo(betaManagedAgentsMcpToolsetParams)
    }
}
