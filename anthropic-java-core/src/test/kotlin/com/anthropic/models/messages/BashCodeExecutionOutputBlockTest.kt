// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionOutputBlockTest {

    @Test
    fun create() {
        val bashCodeExecutionOutputBlock =
            BashCodeExecutionOutputBlock.builder().fileId("file_id").build()

        assertThat(bashCodeExecutionOutputBlock.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionOutputBlock =
            BashCodeExecutionOutputBlock.builder().fileId("file_id").build()

        val roundtrippedBashCodeExecutionOutputBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionOutputBlock),
                jacksonTypeRef<BashCodeExecutionOutputBlock>(),
            )

        assertThat(roundtrippedBashCodeExecutionOutputBlock).isEqualTo(bashCodeExecutionOutputBlock)
    }
}
