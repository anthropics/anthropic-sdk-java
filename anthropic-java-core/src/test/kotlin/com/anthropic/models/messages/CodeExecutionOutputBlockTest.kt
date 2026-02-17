// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionOutputBlockTest {

    @Test
    fun create() {
        val codeExecutionOutputBlock = CodeExecutionOutputBlock.builder().fileId("file_id").build()

        assertThat(codeExecutionOutputBlock.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionOutputBlock = CodeExecutionOutputBlock.builder().fileId("file_id").build()

        val roundtrippedCodeExecutionOutputBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionOutputBlock),
                jacksonTypeRef<CodeExecutionOutputBlock>(),
            )

        assertThat(roundtrippedCodeExecutionOutputBlock).isEqualTo(codeExecutionOutputBlock)
    }
}
