// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InputJsonDeltaTest {

    @Test
    fun createInputJsonDelta() {
        val inputJsonDelta =
            InputJsonDelta.builder()
                .partialJson("partial_json")
                .type(InputJsonDelta.Type.INPUT_JSON_DELTA)
                .build()
        assertThat(inputJsonDelta).isNotNull
        assertThat(inputJsonDelta.partialJson()).isEqualTo("partial_json")
        assertThat(inputJsonDelta.type()).isEqualTo(InputJsonDelta.Type.INPUT_JSON_DELTA)
    }
}
