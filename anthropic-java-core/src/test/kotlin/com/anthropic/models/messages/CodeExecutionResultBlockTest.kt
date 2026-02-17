// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionResultBlockTest {

    @Test
    fun create() {
        val codeExecutionResultBlock =
            CodeExecutionResultBlock.builder()
                .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(codeExecutionResultBlock.content())
            .containsExactly(CodeExecutionOutputBlock.builder().fileId("file_id").build())
        assertThat(codeExecutionResultBlock.returnCode()).isEqualTo(0L)
        assertThat(codeExecutionResultBlock.stderr()).isEqualTo("stderr")
        assertThat(codeExecutionResultBlock.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionResultBlock =
            CodeExecutionResultBlock.builder()
                .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedCodeExecutionResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionResultBlock),
                jacksonTypeRef<CodeExecutionResultBlock>(),
            )

        assertThat(roundtrippedCodeExecutionResultBlock).isEqualTo(codeExecutionResultBlock)
    }
}
