// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsMultiagentSelfParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsMultiagentSelfParams =
            BetaManagedAgentsMultiagentSelfParams.builder()
                .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                .build()

        assertThat(betaManagedAgentsMultiagentSelfParams.type())
            .isEqualTo(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsMultiagentSelfParams =
            BetaManagedAgentsMultiagentSelfParams.builder()
                .type(BetaManagedAgentsMultiagentSelfParams.Type.SELF)
                .build()

        val roundtrippedBetaManagedAgentsMultiagentSelfParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsMultiagentSelfParams),
                jacksonTypeRef<BetaManagedAgentsMultiagentSelfParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsMultiagentSelfParams)
            .isEqualTo(betaManagedAgentsMultiagentSelfParams)
    }
}
