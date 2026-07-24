// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChangeToolReferenceTest {

    @Test
    fun create() {
        val betaToolChangeToolReference = BetaToolChangeToolReference.builder().name("name").build()

        assertThat(betaToolChangeToolReference.name()).isEqualTo("name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChangeToolReference = BetaToolChangeToolReference.builder().name("name").build()

        val roundtrippedBetaToolChangeToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChangeToolReference),
                jacksonTypeRef<BetaToolChangeToolReference>(),
            )

        assertThat(roundtrippedBetaToolChangeToolReference).isEqualTo(betaToolChangeToolReference)
    }
}
