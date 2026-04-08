// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMcpToolsetDefaultConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMcpToolsetDefaultConfigParams =
            BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsMcpToolsetDefaultConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsMcpToolsetDefaultConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsMcpToolsetDefaultConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMcpToolsetDefaultConfigParams =
            BetaManagedAgentsMcpToolsetDefaultConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsMcpToolsetDefaultConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMcpToolsetDefaultConfigParams),
                jacksonTypeRef<BetaManagedAgentsMcpToolsetDefaultConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMcpToolsetDefaultConfigParams)
            .isEqualTo(betaManagedAgentsMcpToolsetDefaultConfigParams)
    }
}
