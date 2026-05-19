// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments.work

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSelfHostedWorkStopRequestTest {

    @Test
    fun create() {
        val betaSelfHostedWorkStopRequest =
            BetaSelfHostedWorkStopRequest.builder().force(true).build()

        assertThat(betaSelfHostedWorkStopRequest.force()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSelfHostedWorkStopRequest =
            BetaSelfHostedWorkStopRequest.builder().force(true).build()

        val roundtrippedBetaSelfHostedWorkStopRequest =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSelfHostedWorkStopRequest),
                jacksonTypeRef<BetaSelfHostedWorkStopRequest>(),
            )

        assertThat(roundtrippedBetaSelfHostedWorkStopRequest)
            .isEqualTo(betaSelfHostedWorkStopRequest)
    }
}
