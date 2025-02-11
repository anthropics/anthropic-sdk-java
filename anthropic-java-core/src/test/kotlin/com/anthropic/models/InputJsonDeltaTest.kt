// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InputJsonDeltaTest {

    @Test
    fun createInputJsonDelta() {
        val inputJsonDelta = InputJsonDelta.builder().partialJson("partial_json").build()
        assertThat(inputJsonDelta).isNotNull
        assertThat(inputJsonDelta.partialJson()).isEqualTo("partial_json")
    }
}
