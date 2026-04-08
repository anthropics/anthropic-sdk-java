// File generated from our OpenAPI spec by Stainless.

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
                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                .build()
                        )
                        .build()
                )
                .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401.configs())
            .containsExactly(
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
        assertThat(betaManagedAgentsAgentToolset20260401.defaultConfig())
            .isEqualTo(
                BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                            .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                            .build()
                    )
                    .build()
            )
        assertThat(betaManagedAgentsAgentToolset20260401.type())
            .isEqualTo(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401 =
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
                            BetaManagedAgentsAlwaysAllowPolicy.builder()
                                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                                .build()
                        )
                        .build()
                )
                .type(BetaManagedAgentsAgentToolset20260401.Type.AGENT_TOOLSET_20260401)
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
