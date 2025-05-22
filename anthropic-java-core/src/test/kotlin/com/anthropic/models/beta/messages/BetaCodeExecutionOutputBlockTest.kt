// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionOutputBlockTest {

    @Test
    fun create() {
        val betaCodeExecutionOutputBlock =
            BetaCodeExecutionOutputBlock.builder().fileId("file_id").build()

        assertThat(betaCodeExecutionOutputBlock.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionOutputBlock =
            BetaCodeExecutionOutputBlock.builder().fileId("file_id").build()

        val roundtrippedBetaCodeExecutionOutputBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionOutputBlock),
                jacksonTypeRef<BetaCodeExecutionOutputBlock>(),
            )

        assertThat(roundtrippedBetaCodeExecutionOutputBlock).isEqualTo(betaCodeExecutionOutputBlock)
    }
}
