// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextDeltaTest {

    @Test
    fun create() {
        val textDelta = TextDelta.of("text")

        assertThat(textDelta.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textDelta = TextDelta.of("text")

        val roundtrippedTextDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textDelta),
                jacksonTypeRef<TextDelta>(),
            )

        assertThat(roundtrippedTextDelta).isEqualTo(textDelta)
    }
}
