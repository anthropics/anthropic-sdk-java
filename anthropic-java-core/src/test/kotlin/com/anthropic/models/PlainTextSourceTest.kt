// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlainTextSourceTest {

    @Test
    fun createPlainTextSource() {
        val plainTextSource = PlainTextSource.builder().data("data").build()
        assertThat(plainTextSource).isNotNull
        assertThat(plainTextSource.data()).isEqualTo("data")
    }
}
