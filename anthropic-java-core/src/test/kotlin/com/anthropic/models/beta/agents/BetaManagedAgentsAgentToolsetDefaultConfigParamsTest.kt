// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolsetDefaultConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolsetDefaultConfigParams =
            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        assertThat(betaManagedAgentsAgentToolsetDefaultConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsAgentToolsetDefaultConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsAgentToolsetDefaultConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolsetDefaultConfigParams =
            BetaManagedAgentsAgentToolsetDefaultConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.builder()
                        .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                        .build()
                )
                .build()

        val roundtrippedBetaManagedAgentsAgentToolsetDefaultConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolsetDefaultConfigParams),
                jacksonTypeRef<BetaManagedAgentsAgentToolsetDefaultConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolsetDefaultConfigParams)
            .isEqualTo(betaManagedAgentsAgentToolsetDefaultConfigParams)
    }
}
