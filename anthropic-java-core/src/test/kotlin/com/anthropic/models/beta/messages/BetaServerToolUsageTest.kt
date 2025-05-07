// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolUsageTest {

    @Test
    fun create() {
        val betaServerToolUsage = BetaServerToolUsage.builder().webSearchRequests(0L).build()

        assertThat(betaServerToolUsage.webSearchRequests()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolUsage = BetaServerToolUsage.builder().webSearchRequests(0L).build()

        val roundtrippedBetaServerToolUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolUsage),
                jacksonTypeRef<BetaServerToolUsage>(),
            )

        assertThat(roundtrippedBetaServerToolUsage).isEqualTo(betaServerToolUsage)
    }
}
