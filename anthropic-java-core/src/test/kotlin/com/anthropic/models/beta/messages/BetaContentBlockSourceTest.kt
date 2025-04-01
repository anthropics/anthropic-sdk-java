// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaContentBlockSourceTest {

    @Test
    fun create() {
        val betaContentBlockSource = BetaContentBlockSource.builder().content("string").build()

        assertThat(betaContentBlockSource.content())
            .isEqualTo(BetaContentBlockSource.Content.ofString("string"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlockSource = BetaContentBlockSource.builder().content("string").build()

        val roundtrippedBetaContentBlockSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlockSource),
                jacksonTypeRef<BetaContentBlockSource>(),
            )

        assertThat(roundtrippedBetaContentBlockSource).isEqualTo(betaContentBlockSource)
    }
}
