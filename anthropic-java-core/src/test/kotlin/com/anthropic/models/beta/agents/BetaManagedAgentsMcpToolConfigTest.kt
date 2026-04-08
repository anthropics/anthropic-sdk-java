// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpToolConfig =
            BetaManagedAgentsMcpToolConfig.builder()
                .enabled(true)
                .name("name")
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsMcpToolConfig.name()).isEqualTo("name")
        assertThat(betaManagedAgentsMcpToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsMcpToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolConfig =
            BetaManagedAgentsMcpToolConfig.builder()
                .enabled(true)
                .name("name")
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpToolConfig),
                jacksonTypeRef<BetaManagedAgentsMcpToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpToolConfig)
            .isEqualTo(betaManagedAgentsMcpToolConfig)
    }
}
