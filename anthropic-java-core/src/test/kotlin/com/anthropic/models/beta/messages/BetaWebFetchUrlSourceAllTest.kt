package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchUrlSourceAllTest {

    @Test
    fun create() {
        val betaWebFetchUrlSourceAll = BetaWebFetchUrlSourceAll.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchUrlSourceAll = BetaWebFetchUrlSourceAll.builder().build()

        val roundtrippedBetaWebFetchUrlSourceAll =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchUrlSourceAll),
                jacksonTypeRef<BetaWebFetchUrlSourceAll>(),
            )

        assertThat(roundtrippedBetaWebFetchUrlSourceAll).isEqualTo(betaWebFetchUrlSourceAll)
    }
}
