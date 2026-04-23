// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionBlockTest {

    @Test
    fun create() {
        val betaCompactionBlock = BetaCompactionBlock.builder().content("content").build()

        assertThat(betaCompactionBlock.content()).contains("content")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionBlock = BetaCompactionBlock.builder().content("content").build()

        val roundtrippedBetaCompactionBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionBlock),
                jacksonTypeRef<BetaCompactionBlock>(),
            )

        assertThat(roundtrippedBetaCompactionBlock).isEqualTo(betaCompactionBlock)
    }
}
