// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CitationsConfigParamTest {

    @Test
    fun createCitationsConfigParam() {
        val citationsConfigParam = CitationsConfigParam.builder().enabled(true).build()
        assertThat(citationsConfigParam).isNotNull
        assertThat(citationsConfigParam.enabled()).contains(true)
    }
}
