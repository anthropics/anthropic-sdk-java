package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingMismatchAllowedInputTransformationTest {

    @Test
    fun create() {
        val betaThinkingMismatchAllowedInputTransformation =
            BetaThinkingMismatchAllowedInputTransformation.builder()
                .path("path")
                .reason(
                    BetaThinkingMismatchAllowedInputTransformation.Reason.MODEL_BINDING_MISMATCH
                )
                .build()

        assertThat(betaThinkingMismatchAllowedInputTransformation.path()).isEqualTo("path")
        assertThat(betaThinkingMismatchAllowedInputTransformation.reason())
            .isEqualTo(BetaThinkingMismatchAllowedInputTransformation.Reason.MODEL_BINDING_MISMATCH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingMismatchAllowedInputTransformation =
            BetaThinkingMismatchAllowedInputTransformation.builder()
                .path("path")
                .reason(
                    BetaThinkingMismatchAllowedInputTransformation.Reason.MODEL_BINDING_MISMATCH
                )
                .build()

        val roundtrippedBetaThinkingMismatchAllowedInputTransformation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingMismatchAllowedInputTransformation),
                jacksonTypeRef<BetaThinkingMismatchAllowedInputTransformation>(),
            )

        assertThat(roundtrippedBetaThinkingMismatchAllowedInputTransformation)
            .isEqualTo(betaThinkingMismatchAllowedInputTransformation)
    }
}
