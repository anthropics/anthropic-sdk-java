// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolsetDefaultConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolsetDefaultConfig =
            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsAgentToolsetDefaultConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsAgentToolsetDefaultConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsAgentToolsetDefaultConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolsetDefaultConfig =
            BetaManagedAgentsAgentToolsetDefaultConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsAgentToolsetDefaultConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolsetDefaultConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolsetDefaultConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolsetDefaultConfig)
            .isEqualTo(betaManagedAgentsAgentToolsetDefaultConfig)
    }
}
