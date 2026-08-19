// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGrepToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsGrepToolConfig =
            BetaManagedAgentsGrepToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        assertThat(betaManagedAgentsGrepToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsGrepToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsGrepToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGrepToolConfig =
            BetaManagedAgentsGrepToolConfig.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .build()

        val roundtrippedBetaManagedAgentsGrepToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGrepToolConfig),
                jacksonTypeRef<BetaManagedAgentsGrepToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGrepToolConfig)
            .isEqualTo(betaManagedAgentsGrepToolConfig)
    }
}
