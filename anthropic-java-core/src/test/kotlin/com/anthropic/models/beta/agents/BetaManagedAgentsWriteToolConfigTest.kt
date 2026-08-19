// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWriteToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsWriteToolConfig =
            BetaManagedAgentsWriteToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsWriteToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsWriteToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsWriteToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWriteToolConfig =
            BetaManagedAgentsWriteToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsWriteToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWriteToolConfig),
                jacksonTypeRef<BetaManagedAgentsWriteToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWriteToolConfig)
            .isEqualTo(betaManagedAgentsWriteToolConfig)
    }
}
