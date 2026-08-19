// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsEditToolConfigParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsEditToolConfigParams =
            BetaManagedAgentsEditToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsEditToolConfigParams.Type.EDIT)
                .build()

        assertThat(betaManagedAgentsEditToolConfigParams.enabled()).contains(true)
        assertThat(betaManagedAgentsEditToolConfigParams.permissionPolicy())
            .contains(
                BetaManagedAgentsEditToolConfigParams.PermissionPolicy.ofAlwaysAllow(
                    BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                )
            )
        assertThat(betaManagedAgentsEditToolConfigParams.type())
            .contains(BetaManagedAgentsEditToolConfigParams.Type.EDIT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsEditToolConfigParams =
            BetaManagedAgentsEditToolConfigParams.builder()
                .enabled(true)
                .permissionPolicy(
                    BetaManagedAgentsAlwaysAllowPolicy.of(
                        BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW
                    )
                )
                .type(BetaManagedAgentsEditToolConfigParams.Type.EDIT)
                .build()

        val roundtrippedBetaManagedAgentsEditToolConfigParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsEditToolConfigParams),
                jacksonTypeRef<BetaManagedAgentsEditToolConfigParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsEditToolConfigParams)
            .isEqualTo(betaManagedAgentsEditToolConfigParams)
    }
}
