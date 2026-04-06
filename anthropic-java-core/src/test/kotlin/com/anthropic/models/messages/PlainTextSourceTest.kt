// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlainTextSourceTest {

    @Test
    fun create() {
        val plainTextSource = PlainTextSource.builder().data("data").build()

        assertThat(plainTextSource.data()).isEqualTo("data")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val plainTextSource = PlainTextSource.builder().data("data").build()

        val roundtrippedPlainTextSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(plainTextSource),
                jacksonTypeRef<PlainTextSource>(),
            )

        assertThat(roundtrippedPlainTextSource).isEqualTo(plainTextSource)
    }
}
