// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPlainTextSourceTest {

    @Test
    fun create() {
        val betaPlainTextSource = BetaPlainTextSource.builder().data("data").build()

        assertThat(betaPlainTextSource.data()).isEqualTo("data")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPlainTextSource = BetaPlainTextSource.builder().data("data").build()

        val roundtrippedBetaPlainTextSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPlainTextSource),
                jacksonTypeRef<BetaPlainTextSource>(),
            )

        assertThat(roundtrippedBetaPlainTextSource).isEqualTo(betaPlainTextSource)
    }
}
