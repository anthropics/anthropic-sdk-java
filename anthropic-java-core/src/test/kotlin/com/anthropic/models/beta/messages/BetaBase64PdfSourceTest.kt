// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBase64PdfSourceTest {

    @Test
    fun createBetaBase64PdfSource() {
        val betaBase64PdfSource = BetaBase64PdfSource.builder().data("U3RhaW5sZXNzIHJvY2tz").build()
        assertThat(betaBase64PdfSource).isNotNull
        assertThat(betaBase64PdfSource.data()).isEqualTo("U3RhaW5sZXNzIHJvY2tz")
    }
}
