// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlainTextSourceTest {

    @Test
    fun create() {
        val plainTextSource = PlainTextSource.builder().data("data").build()

        assertThat(plainTextSource.data()).isEqualTo("data")
    }
}
