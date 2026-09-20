package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolChangeToolReferenceTest {

    @Test
    fun create() {
        val betaResponseToolChangeToolReference = BetaResponseToolChangeToolReference.of("name")

        assertThat(betaResponseToolChangeToolReference.name()).isEqualTo("name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolChangeToolReference = BetaResponseToolChangeToolReference.of("name")

        val roundtrippedBetaResponseToolChangeToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolChangeToolReference),
                jacksonTypeRef<BetaResponseToolChangeToolReference>(),
            )

        assertThat(roundtrippedBetaResponseToolChangeToolReference)
            .isEqualTo(betaResponseToolChangeToolReference)
    }
}
