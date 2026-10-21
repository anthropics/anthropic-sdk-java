package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401Test {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401 =
            BetaManagedAgentsAgentToolset20260401.builder()
                .addConfig(
                    BetaManagedAgentsBashToolConfig.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401.configs())
            .containsExactly(
                BetaManagedAgentsAgentToolConfig.ofBash(
                    BetaManagedAgentsBashToolConfig.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentToolset20260401.defaultConfig())
            .isEqualTo(
                BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401 =
            BetaManagedAgentsAgentToolset20260401.builder()
                .addConfig(
                    BetaManagedAgentsBashToolConfig.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401)
            .isEqualTo(betaManagedAgentsAgentToolset20260401)
    }
}
