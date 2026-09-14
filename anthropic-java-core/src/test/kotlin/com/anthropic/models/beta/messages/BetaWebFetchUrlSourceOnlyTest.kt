package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchUrlSourceOnlyTest {

    @Test
    fun create() {
        val betaWebFetchUrlSourceOnly =
            BetaWebFetchUrlSourceOnly.builder()
                .addTool(BetaWebFetchUrlSourceToolReference.of("name"))
                .build()

        assertThat(betaWebFetchUrlSourceOnly.tools())
            .containsExactly(BetaWebFetchUrlSourceToolReference.of("name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchUrlSourceOnly =
            BetaWebFetchUrlSourceOnly.builder()
                .addTool(BetaWebFetchUrlSourceToolReference.of("name"))
                .build()

        val roundtrippedBetaWebFetchUrlSourceOnly =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchUrlSourceOnly),
                jacksonTypeRef<BetaWebFetchUrlSourceOnly>(),
            )

        assertThat(roundtrippedBetaWebFetchUrlSourceOnly).isEqualTo(betaWebFetchUrlSourceOnly)
    }
}
