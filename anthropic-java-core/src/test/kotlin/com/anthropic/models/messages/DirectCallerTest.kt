// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DirectCallerTest {

    @Test
    fun create() {
        val directCaller = DirectCaller.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val directCaller = DirectCaller.builder().build()

        val roundtrippedDirectCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(directCaller),
                jacksonTypeRef<DirectCaller>(),
            )

        assertThat(roundtrippedDirectCaller).isEqualTo(directCaller)
    }
}
