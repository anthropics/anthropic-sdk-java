// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionResultBlockParamTest {

    @Test
    fun create() {
        val codeExecutionResultBlockParam =
            CodeExecutionResultBlockParam.builder()
                .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(codeExecutionResultBlockParam.content())
            .containsExactly(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
        assertThat(codeExecutionResultBlockParam.returnCode()).isEqualTo(0L)
        assertThat(codeExecutionResultBlockParam.stderr()).isEqualTo("stderr")
        assertThat(codeExecutionResultBlockParam.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionResultBlockParam =
            CodeExecutionResultBlockParam.builder()
                .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedCodeExecutionResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionResultBlockParam),
                jacksonTypeRef<CodeExecutionResultBlockParam>(),
            )

        assertThat(roundtrippedCodeExecutionResultBlockParam)
            .isEqualTo(codeExecutionResultBlockParam)
    }
}
