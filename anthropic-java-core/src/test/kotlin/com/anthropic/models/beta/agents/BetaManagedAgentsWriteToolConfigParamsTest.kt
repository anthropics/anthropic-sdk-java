package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsWriteToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsWriteToolConfigParams =
            BetaManagedAgentsWriteToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsWriteToolConfigParams.Type.WRITE)
                .build()

        assertThat(betaManagedAgentsWriteToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsWriteToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsWriteToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.builder().build()
                )
            )
        assertThat(betaManagedAgentsWriteToolConfigParams.type())
            .contains(BetaManagedAgentsWriteToolConfigParams.Type.WRITE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsWriteToolConfigParams =
            BetaManagedAgentsWriteToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(BetaManagedAgentsAlwaysAllowPolicy.builder().build())
                .type(BetaManagedAgentsWriteToolConfigParams.Type.WRITE)
                .build()

        val roundtrippedBetaManagedAgentsWriteToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsWriteToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsWriteToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsWriteToolConfigParams)
            .isEqualTo(betaManagedAgentsWriteToolConfigParams)
    }
}
