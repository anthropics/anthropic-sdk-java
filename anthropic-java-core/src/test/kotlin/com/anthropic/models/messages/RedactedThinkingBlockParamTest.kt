// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RedactedThinkingBlockParamTest {

    @Test
    fun create() {
        val redactedThinkingBlockParam = RedactedThinkingBlockParam.builder().data("data").build()

        assertThat(redactedThinkingBlockParam.data()).isEqualTo("data")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val redactedThinkingBlockParam = RedactedThinkingBlockParam.builder().data("data").build()

        val roundtrippedRedactedThinkingBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(redactedThinkingBlockParam),
                jacksonTypeRef<RedactedThinkingBlockParam>(),
            )

        assertThat(roundtrippedRedactedThinkingBlockParam).isEqualTo(redactedThinkingBlockParam)
    }
}
