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
                .addConfig(
                    BetaManagedAgentsMcpToolConfigParams.builder()
                        .name("x")
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolsetParams.mcpServerName()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpToolsetParams.configs().getOrNull())
            .containsExactly(
                BetaManagedAgentsMcpToolConfigParams.builder()
                    .name("x")
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )
        assertThat(betaManagedAgentsMcpToolsetParams.defaultConfig())
            .contains(
                BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsMcpToolsetParams = BetaManagedAgentsMcpToolsetParams.of("x")

        val betaManagedAgentsMcpToolsetParams =
            baseBetaManagedAgentsMcpToolsetParams
                .toBuilder()
                .addConfig(
                    BetaManagedAgentsMcpToolConfigParams.builder()
                        .name("x")
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolsetParams.configs().getOrNull())
            .containsExactly(
                BetaManagedAgentsMcpToolConfigParams.builder()
                    .name("x")
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolsetParams =
            BetaManagedAgentsMcpToolsetParams.builder()
                .mcpServerName("x")
                .addConfig(
                    BetaManagedAgentsMcpToolConfigParams.builder()
                        .name("x")
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
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
