package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchUrlSourceNoneTest {

    @Test
    fun create() {
        val betaWebFetchUrlSourceNone = BetaWebFetchUrlSourceNone.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchUrlSourceNone = BetaWebFetchUrlSourceNone.builder().build()

        val roundtrippedBetaWebFetchUrlSourceNone =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchUrlSourceNone),
                jacksonTypeRef<BetaWebFetchUrlSourceNone>(),
            )

        assertThat(roundtrippedBetaWebFetchUrlSourceNone).isEqualTo(betaWebFetchUrlSourceNone)
    }
}
