package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOutputBehaviorUpdateExistingTest {

    @Test
    fun create() {
        val betaOutputBehaviorUpdateExisting = BetaOutputBehaviorUpdateExisting.of("x")

        assertThat(betaOutputBehaviorUpdateExisting.memoryStoreId()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputBehaviorUpdateExisting = BetaOutputBehaviorUpdateExisting.of("x")

        val roundtrippedBetaOutputBehaviorUpdateExisting =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputBehaviorUpdateExisting),
                jacksonTypeRef<BetaOutputBehaviorUpdateExisting>(),
            )

        assertThat(roundtrippedBetaOutputBehaviorUpdateExisting)
            .isEqualTo(betaOutputBehaviorUpdateExisting)
    }
}
