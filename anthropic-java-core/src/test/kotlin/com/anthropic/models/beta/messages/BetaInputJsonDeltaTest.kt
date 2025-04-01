// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaInputJsonDeltaTest {

    @Test
    fun create() {
        val betaInputJsonDelta = BetaInputJsonDelta.builder().partialJson("partial_json").build()

        assertThat(betaInputJsonDelta.partialJson()).isEqualTo("partial_json")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaInputJsonDelta = BetaInputJsonDelta.builder().partialJson("partial_json").build()

        val roundtrippedBetaInputJsonDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInputJsonDelta),
                jacksonTypeRef<BetaInputJsonDelta>(),
            )

        assertThat(roundtrippedBetaInputJsonDelta).isEqualTo(betaInputJsonDelta)
    }
}
