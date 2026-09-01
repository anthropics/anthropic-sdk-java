// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingDroppedInputTransformationTest {

    @Test
    fun create() {
        val betaThinkingDroppedInputTransformation =
            BetaThinkingDroppedInputTransformation.builder()
                .path("path")
                .reason(BetaThinkingDroppedInputTransformation.Reason.MODEL_BINDING_MISMATCH)
                .build()

        assertThat(betaThinkingDroppedInputTransformation.path()).isEqualTo("path")
        assertThat(betaThinkingDroppedInputTransformation.reason())
            .isEqualTo(BetaThinkingDroppedInputTransformation.Reason.MODEL_BINDING_MISMATCH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingDroppedInputTransformation =
            BetaThinkingDroppedInputTransformation.builder()
                .path("path")
                .reason(BetaThinkingDroppedInputTransformation.Reason.MODEL_BINDING_MISMATCH)
                .build()

        val roundtrippedBetaThinkingDroppedInputTransformation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingDroppedInputTransformation),
                jacksonTypeRef<BetaThinkingDroppedInputTransformation>(),
            )

        assertThat(roundtrippedBetaThinkingDroppedInputTransformation)
            .isEqualTo(betaThinkingDroppedInputTransformation)
    }
}
