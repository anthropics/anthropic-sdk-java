// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionContentBlockDeltaTest {

    @Test
    fun create() {
        val betaCompactionContentBlockDelta =
            BetaCompactionContentBlockDelta.builder().content("content").build()

        assertThat(betaCompactionContentBlockDelta.content()).contains("content")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionContentBlockDelta =
            BetaCompactionContentBlockDelta.builder().content("content").build()

        val roundtrippedBetaCompactionContentBlockDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionContentBlockDelta),
                jacksonTypeRef<BetaCompactionContentBlockDelta>(),
            )

        assertThat(roundtrippedBetaCompactionContentBlockDelta)
            .isEqualTo(betaCompactionContentBlockDelta)
    }
}
