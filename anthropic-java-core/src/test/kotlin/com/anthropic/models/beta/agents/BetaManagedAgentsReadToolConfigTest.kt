package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsReadToolConfigTest {

    @Test
    fun create() {
        val betaManagedAgentsReadToolConfig =
            BetaManagedAgentsReadToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        assertThat(betaManagedAgentsReadToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsReadToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsReadToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsReadToolConfig =
            BetaManagedAgentsReadToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsReadToolConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsReadToolConfig),
                jacksonTypeRef<BetaManagedAgentsReadToolConfig>(),
            )

        assertThat(roundtrippedBetaManagedAgentsReadToolConfig)
            .isEqualTo(betaManagedAgentsReadToolConfig)
    }
}
