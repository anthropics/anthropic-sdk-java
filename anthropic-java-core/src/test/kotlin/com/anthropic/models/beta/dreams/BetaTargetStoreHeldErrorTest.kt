// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTargetStoreHeldErrorTest {

    @Test
    fun create() {
        val betaTargetStoreHeldError = BetaTargetStoreHeldError.builder().message("message").build()

        assertThat(betaTargetStoreHeldError.message()).contains("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTargetStoreHeldError = BetaTargetStoreHeldError.builder().message("message").build()

        val roundtrippedBetaTargetStoreHeldError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTargetStoreHeldError),
                jacksonTypeRef<BetaTargetStoreHeldError>(),
            )

        assertThat(roundtrippedBetaTargetStoreHeldError).isEqualTo(betaTargetStoreHeldError)
    }
}
