// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOutputBehaviorCreateNewTest {

    @Test
    fun create() {
        val betaOutputBehaviorCreateNew =
            BetaOutputBehaviorCreateNew.of(BetaOutputBehaviorCreateNew.Type.CREATE_NEW)

        assertThat(betaOutputBehaviorCreateNew.type())
            .isEqualTo(BetaOutputBehaviorCreateNew.Type.CREATE_NEW)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOutputBehaviorCreateNew =
            BetaOutputBehaviorCreateNew.of(BetaOutputBehaviorCreateNew.Type.CREATE_NEW)

        val roundtrippedBetaOutputBehaviorCreateNew =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOutputBehaviorCreateNew),
                jacksonTypeRef<BetaOutputBehaviorCreateNew>(),
            )

        assertThat(roundtrippedBetaOutputBehaviorCreateNew).isEqualTo(betaOutputBehaviorCreateNew)
    }
}
