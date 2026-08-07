// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpToolConfigParams =
            BetaManagedAgentsMcpToolConfigParams.builder()
                .name("x")
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsMcpToolConfigParams.name()).isEqualTo("x")
        assertThat(betaManagedAgentsMcpToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsMcpToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsMcpToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolConfigParams =
            BetaManagedAgentsMcpToolConfigParams.builder()
                .name("x")
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsMcpToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpToolConfigParams)
            .isEqualTo(betaManagedAgentsMcpToolConfigParams)
    }
}
