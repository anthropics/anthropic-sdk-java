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
                .addConfig(
                    BetaManagedAgentsBashToolConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401Params.configs().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentToolConfigParams.ofBash(
                    BetaManagedAgentsBashToolConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                        .build()
                )
            )
        assertThat(betaManagedAgentsAgentToolset20260401Params.defaultConfig())
            .contains(
                BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                    .enabled(true)
                    .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsAgentToolset20260401Params =
            BetaManagedAgentsAgentToolset20260401Params.builder().build()

        val betaManagedAgentsAgentToolset20260401Params =
            baseBetaManagedAgentsAgentToolset20260401Params
                .toBuilder()
                .addConfig(
                    BetaManagedAgentsAgentToolConfigParams.ofBash(
                        BetaManagedAgentsBashToolConfigParams.builder()
                            .enabled(true)
                            .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                            .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                            .build()
                    )
                )
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401Params.configs().getOrNull())
            .containsExactly(
                BetaManagedAgentsAgentToolConfigParams.ofBash(
                    BetaManagedAgentsBashToolConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401Params =
            BetaManagedAgentsAgentToolset20260401Params.builder()
                .addConfig(
                    BetaManagedAgentsBashToolConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                        .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                        .build()
                )
                .defaultConfig(
                    BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                        .enabled(true)
                        .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
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
