// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsGrepToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsGrepToolConfigParams =
            BetaManagedAgentsGrepToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsGrepToolConfigParams.Type.GREP)
                .build()

        assertThat(betaManagedAgentsGrepToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsGrepToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsGrepToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsGrepToolConfigParams.type())
            .contains(BetaManagedAgentsGrepToolConfigParams.Type.GREP)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsGrepToolConfigParams =
            BetaManagedAgentsGrepToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsGrepToolConfigParams.Type.GREP)
                .build()

        val roundtrippedBetaManagedAgentsGrepToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsGrepToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsGrepToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsGrepToolConfigParams)
            .isEqualTo(betaManagedAgentsGrepToolConfigParams)
    }
}
