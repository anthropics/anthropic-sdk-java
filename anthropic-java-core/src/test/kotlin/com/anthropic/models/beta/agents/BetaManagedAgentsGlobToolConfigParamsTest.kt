package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGlobToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsGlobToolConfigParams =
            BetaManagedAgentsGlobToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsGlobToolConfigParams.Type.GLOB)
                .build()

        assertThat(betaManagedAgentsGlobToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsGlobToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsGlobToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                )
            )
        assertThat(betaManagedAgentsGlobToolConfigParams.type())
            .contains(BetaManagedAgentsGlobToolConfigParams.Type.GLOB)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGlobToolConfigParams =
            BetaManagedAgentsGlobToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsGlobToolConfigParams.Type.GLOB)
                .build()

        val roundtrippedBetaManagedAgentsGlobToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGlobToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsGlobToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGlobToolConfigParams)
            .isEqualTo(betaManagedAgentsGlobToolConfigParams)
    }
}
