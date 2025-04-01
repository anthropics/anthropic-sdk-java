// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RedactedThinkingBlockTest {

    @Test
    fun create() {
        val redactedThinkingBlock = RedactedThinkingBlock.builder().data("data").build()

        assertThat(redactedThinkingBlock.data()).isEqualTo("data")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val redactedThinkingBlock = RedactedThinkingBlock.builder().data("data").build()

        val roundtrippedRedactedThinkingBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(redactedThinkingBlock),
                jacksonTypeRef<RedactedThinkingBlock>(),
            )

        assertThat(roundtrippedRedactedThinkingBlock).isEqualTo(redactedThinkingBlock)
    }
}
