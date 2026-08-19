// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEditToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsEditToolConfig =
            BetaManagedAgentsEditToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsEditToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsEditToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsEditToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEditToolConfig =
            BetaManagedAgentsEditToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsEditToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEditToolConfig),
                jacksonTypeRef<BetaManagedAgentsEditToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEditToolConfig)
            .isEqualTo(betaManagedAgentsEditToolConfig)
    }
}
