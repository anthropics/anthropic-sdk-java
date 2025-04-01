// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSignatureDeltaTest {

    @Test
    fun create() {
        val betaSignatureDelta = BetaSignatureDelta.builder().signature("signature").build()

        assertThat(betaSignatureDelta.signature()).isEqualTo("signature")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSignatureDelta = BetaSignatureDelta.builder().signature("signature").build()

        val roundtrippedBetaSignatureDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSignatureDelta),
                jacksonTypeRef<BetaSignatureDelta>(),
            )

        assertThat(roundtrippedBetaSignatureDelta).isEqualTo(betaSignatureDelta)
    }
}
