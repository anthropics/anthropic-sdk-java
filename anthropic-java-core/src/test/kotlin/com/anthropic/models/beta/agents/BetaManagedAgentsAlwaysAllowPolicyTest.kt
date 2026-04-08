// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAlwaysAllowPolicyTest {

    @Test
    fun create() {
        val betaManagedAgentsAlwaysAllowPolicy =
            BetaManagedAgentsAlwaysAllowPolicy.builder()
                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                .build()

        assertThat(betaManagedAgentsAlwaysAllowPolicy.type())
            .isEqualTo(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAlwaysAllowPolicy =
            BetaManagedAgentsAlwaysAllowPolicy.builder()
                .type(BetaManagedAgentsAlwaysAllowPolicy.Type.ALWAYS_ALLOW)
                .build()

        val roundtrippedBetaManagedAgentsAlwaysAllowPolicy =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAlwaysAllowPolicy),
                jacksonTypeRef<BetaManagedAgentsAlwaysAllowPolicy>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAlwaysAllowPolicy)
            .isEqualTo(betaManagedAgentsAlwaysAllowPolicy)
    }
}
