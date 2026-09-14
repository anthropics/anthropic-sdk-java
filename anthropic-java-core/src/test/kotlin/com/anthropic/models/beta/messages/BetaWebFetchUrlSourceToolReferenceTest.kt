package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchUrlSourceToolReferenceTest {

    @Test
    fun create() {
        val betaWebFetchUrlSourceToolReference = BetaWebFetchUrlSourceToolReference.of("name")

        assertThat(betaWebFetchUrlSourceToolReference.name()).isEqualTo("name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchUrlSourceToolReference = BetaWebFetchUrlSourceToolReference.of("name")

        val roundtrippedBetaWebFetchUrlSourceToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchUrlSourceToolReference),
                jacksonTypeRef<BetaWebFetchUrlSourceToolReference>(),
            )

        assertThat(roundtrippedBetaWebFetchUrlSourceToolReference)
            .isEqualTo(betaWebFetchUrlSourceToolReference)
    }
}
