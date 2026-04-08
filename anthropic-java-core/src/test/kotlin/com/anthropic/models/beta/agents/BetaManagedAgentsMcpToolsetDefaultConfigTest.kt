// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpToolsetDefaultConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpToolsetDefaultConfig =
            BetaManagedAgentsMcpToolsetDefaultConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolsetDefaultConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsMcpToolsetDefaultConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsMcpToolsetDefaultConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolsetDefaultConfig =
            BetaManagedAgentsMcpToolsetDefaultConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpToolsetDefaultConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpToolsetDefaultConfig),
                jacksonTypeRef<BetaManagedAgentsMcpToolsetDefaultConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpToolsetDefaultConfig)
            .isEqualTo(betaManagedAgentsMcpToolsetDefaultConfig)
    }
}
