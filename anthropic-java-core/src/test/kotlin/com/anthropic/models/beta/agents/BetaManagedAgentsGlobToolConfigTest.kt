// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGlobToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsGlobToolConfig =
            BetaManagedAgentsGlobToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsGlobToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsGlobToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsGlobToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGlobToolConfig =
            BetaManagedAgentsGlobToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsGlobToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGlobToolConfig),
                jacksonTypeRef<BetaManagedAgentsGlobToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGlobToolConfig)
            .isEqualTo(betaManagedAgentsGlobToolConfig)
    }
}
