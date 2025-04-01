// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InputJsonDeltaTest {

    @Test
    fun create() {
        val inputJsonDelta = InputJsonDelta.builder().partialJson("partial_json").build()

        assertThat(inputJsonDelta.partialJson()).isEqualTo("partial_json")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val inputJsonDelta = InputJsonDelta.builder().partialJson("partial_json").build()

        val roundtrippedInputJsonDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(inputJsonDelta),
                jacksonTypeRef<InputJsonDelta>(),
            )

        assertThat(roundtrippedInputJsonDelta).isEqualTo(inputJsonDelta)
    }
}
