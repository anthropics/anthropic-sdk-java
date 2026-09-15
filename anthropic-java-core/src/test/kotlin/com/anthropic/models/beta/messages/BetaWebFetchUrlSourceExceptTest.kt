package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchUrlSourceExceptTest {

    @Test
    fun create() {
        val betaWebFetchUrlSourceExcept =
            BetaWebFetchUrlSourceExcept.builder()
                .addTool(BetaWebFetchUrlSourceToolReference.of("name"))
                .build()

        assertThat(betaWebFetchUrlSourceExcept.tools())
            .containsExactly(BetaWebFetchUrlSourceToolReference.of("name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchUrlSourceExcept =
            BetaWebFetchUrlSourceExcept.builder()
                .addTool(BetaWebFetchUrlSourceToolReference.of("name"))
                .build()

        val roundtrippedBetaWebFetchUrlSourceExcept =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchUrlSourceExcept),
                jacksonTypeRef<BetaWebFetchUrlSourceExcept>(),
            )

        assertThat(roundtrippedBetaWebFetchUrlSourceExcept).isEqualTo(betaWebFetchUrlSourceExcept)
    }
}
