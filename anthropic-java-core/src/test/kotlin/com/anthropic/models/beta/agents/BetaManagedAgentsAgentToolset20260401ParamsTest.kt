// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401ParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401Params =
            BetaManagedAgentsAgentToolset20260401Params.builder()
                .type(BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401)
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

        assertThat(betaManagedAgentsAgentToolset20260401Params.type())
            .isEqualTo(BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401)
        assertThat(betaManagedAgentsAgentToolset20260401Params.configs().getOrNull())
            .containsExactly(
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
        assertThat(betaManagedAgentsAgentToolset20260401Params.defaultConfig())
            .contains(
                BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(
                        BetaManagedAgentsAlwaysAllowPolicy.builder()
                            .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                            .build()
                    )
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401Params =
            BetaManagedAgentsAgentToolset20260401Params.builder()
                .type(BetaManagedAgentsAgentToolset20260401Params.Type.AGENT_TOOLSET_20260401)
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

        val roundtrippedBetaManagedAgentsAgentToolset20260401Params =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401Params),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401Params>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401Params)
            .isEqualTo(betaManagedAgentsAgentToolset20260401Params)
    }
}
