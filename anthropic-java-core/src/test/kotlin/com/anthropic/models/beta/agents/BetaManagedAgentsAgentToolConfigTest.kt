// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.builder()
                .enabled(true)
                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsAgentToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsAgentToolConfig.name())
            .isEqualTo(BetaManagedAgentsAgentToolConfig.Name.BASH)
        assertThat(betaManagedAgentsAgentToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsAgentToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfig =
            BetaManagedAgentsAgentToolConfig.builder()
                .enabled(true)
                .name(BetaManagedAgentsAgentToolConfig.Name.BASH)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsAgentToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfig),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfig)
            .isEqualTo(betaManagedAgentsAgentToolConfig)
    }
}
