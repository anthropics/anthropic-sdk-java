// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationsConfigParamTest {

    @Test
    fun create() {
        val citationsConfigParam = CitationsConfigParam.builder().enabled(true).build()

        assertThat(citationsConfigParam.enabled()).contains(true)
    }
}
