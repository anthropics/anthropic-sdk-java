// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsBashToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsBashToolConfigParams =
            BetaManagedAgentsBashToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                .build()

        assertThat(betaManagedAgentsBashToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsBashToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsBashToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsBashToolConfigParams.type())
            .contains(BetaManagedAgentsBashToolConfigParams.Type.BASH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsBashToolConfigParams =
            BetaManagedAgentsBashToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsBashToolConfigParams.Type.BASH)
                .build()

        val roundtrippedBetaManagedAgentsBashToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsBashToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsBashToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsBashToolConfigParams)
            .isEqualTo(betaManagedAgentsBashToolConfigParams)
    }
}
