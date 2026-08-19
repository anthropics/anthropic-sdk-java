// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsReadToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsReadToolConfigParams =
            BetaManagedAgentsReadToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsReadToolConfigParams.Type.READ)
                .build()

        assertThat(betaManagedAgentsReadToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsReadToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsReadToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsReadToolConfigParams.type())
            .contains(BetaManagedAgentsReadToolConfigParams.Type.READ)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsReadToolConfigParams =
            BetaManagedAgentsReadToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsReadToolConfigParams.Type.READ)
                .build()

        val roundtrippedBetaManagedAgentsReadToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsReadToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsReadToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsReadToolConfigParams)
            .isEqualTo(betaManagedAgentsReadToolConfigParams)
    }
}
