// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBashToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsBashToolConfig =
            BetaManagedAgentsBashToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsBashToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsBashToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsBashToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBashToolConfig =
            BetaManagedAgentsBashToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsBashToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBashToolConfig),
                jacksonTypeRef<BetaManagedAgentsBashToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBashToolConfig)
            .isEqualTo(betaManagedAgentsBashToolConfig)
    }
}
