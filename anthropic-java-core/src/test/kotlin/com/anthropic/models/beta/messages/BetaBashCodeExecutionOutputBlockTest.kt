// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionOutputBlockTest {

    @Test
    fun create() {
        val betaBashCodeExecutionOutputBlock = BetaBashCodeExecutionOutputBlock.of("file_id")

        assertThat(betaBashCodeExecutionOutputBlock.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionOutputBlock = BetaBashCodeExecutionOutputBlock.of("file_id")

        val roundtrippedBetaBashCodeExecutionOutputBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionOutputBlock),
                jacksonTypeRef<BetaBashCodeExecutionOutputBlock>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionOutputBlock)
            .isEqualTo(betaBashCodeExecutionOutputBlock)
    }
}
