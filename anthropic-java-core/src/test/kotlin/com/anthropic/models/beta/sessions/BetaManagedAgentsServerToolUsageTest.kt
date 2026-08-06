// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsServerToolUsageTest {

    @Test
    fun create() {
        val betaManagedAgentsServerToolUsage =
            BetaManagedAgentsServerToolUsage.builder()
                .webFetchRequests(0)
                .webSearchRequests(3)
                .build()

        assertThat(betaManagedAgentsServerToolUsage.webFetchRequests()).contains(0)
        assertThat(betaManagedAgentsServerToolUsage.webSearchRequests()).contains(3)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsServerToolUsage =
            BetaManagedAgentsServerToolUsage.builder()
                .webFetchRequests(0)
                .webSearchRequests(3)
                .build()

        val roundtrippedBetaManagedAgentsServerToolUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsServerToolUsage),
                jacksonTypeRef<BetaManagedAgentsServerToolUsage>(),
            )

        assertThat(roundtrippedBetaManagedAgentsServerToolUsage)
            .isEqualTo(betaManagedAgentsServerToolUsage)
    }
}
