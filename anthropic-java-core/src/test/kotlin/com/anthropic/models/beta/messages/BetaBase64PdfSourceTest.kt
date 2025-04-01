// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBase64PdfSourceTest {

    @Test
    fun create() {
        val betaBase64PdfSource = BetaBase64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()

        assertThat(betaBase64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBase64PdfSource = BetaBase64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()

        val roundtrippedBetaBase64PdfSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBase64PdfSource),
                jacksonTypeRef<BetaBase64PdfSource>(),
            )

        assertThat(roundtrippedBetaBase64PdfSource).isEqualTo(betaBase64PdfSource)
    }
}
