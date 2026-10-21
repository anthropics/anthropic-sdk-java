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
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .build()

        assertThat(betaManagedAgentsGlobToolConfig.enabled()).isEqualTo(true)
        assertThat(betaManagedAgentsGlobToolConfig.permissionPolicy())
            .isEqualTo(
                BetaManagedAgentsGlobToolConfig.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGlobToolConfig =
            BetaManagedAgentsGlobToolConfig.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
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
