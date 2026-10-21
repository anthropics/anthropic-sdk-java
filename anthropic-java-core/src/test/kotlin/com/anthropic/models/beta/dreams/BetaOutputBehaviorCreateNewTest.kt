package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOutputBehaviorCreateNewTest {

    @Test
    fun create() {
        val betaOutputBehaviorCreateNew = BetaOutputBehaviorCreateNew.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputBehaviorCreateNew = BetaOutputBehaviorCreateNew.builder().build()

        val roundtrippedBetaOutputBehaviorCreateNew =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputBehaviorCreateNew),
                jacksonTypeRef<BetaOutputBehaviorCreateNew>(),
            )

        assertThat(roundtrippedBetaOutputBehaviorCreateNew).isEqualTo(betaOutputBehaviorCreateNew)
    }
}
