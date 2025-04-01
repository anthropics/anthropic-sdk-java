// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaUrlPdfSourceTest {

    @Test
    fun create() {
        val betaUrlPdfSource = BetaUrlPdfSource.builder().url("url").build()

        assertThat(betaUrlPdfSource.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaUrlPdfSource = BetaUrlPdfSource.builder().url("url").build()

        val roundtrippedBetaUrlPdfSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaUrlPdfSource),
                jacksonTypeRef<BetaUrlPdfSource>(),
            )

        assertThat(roundtrippedBetaUrlPdfSource).isEqualTo(betaUrlPdfSource)
    }
}
