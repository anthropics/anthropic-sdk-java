// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.builder()
                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsAgentToolConfigParams.name())
            .isEqualTo(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
        assertThat(betaManagedAgentsAgentToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsAgentToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsAgentToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolConfigParams =
            BetaManagedAgentsAgentToolConfigParams.builder()
                .name(BetaManagedAgentsAgentToolConfigParams.Name.BASH)
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsAgentToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolConfigParams)
    }
}
