// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOutputBehaviorUpdateExistingTest {

    @Test
    fun create() {
        val betaOutputBehaviorUpdateExisting =
            BetaOutputBehaviorUpdateExisting.builder()
                .memoryStoreId("x")
                .type(BetaOutputBehaviorUpdateExisting.Type.UPDATE_EXISTING)
                .build()

        assertThat(betaOutputBehaviorUpdateExisting.memoryStoreId()).isEqualTo("x")
        assertThat(betaOutputBehaviorUpdateExisting.type())
            .isEqualTo(BetaOutputBehaviorUpdateExisting.Type.UPDATE_EXISTING)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputBehaviorUpdateExisting =
            BetaOutputBehaviorUpdateExisting.builder()
                .memoryStoreId("x")
                .type(BetaOutputBehaviorUpdateExisting.Type.UPDATE_EXISTING)
                .build()

        val roundtrippedBetaOutputBehaviorUpdateExisting =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputBehaviorUpdateExisting),
                jacksonTypeRef<BetaOutputBehaviorUpdateExisting>(),
            )

        assertThat(roundtrippedBetaOutputBehaviorUpdateExisting)
            .isEqualTo(betaOutputBehaviorUpdateExisting)
    }
}
