// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionOutputBlockParamTest {

    @Test
    fun create() {
        val codeExecutionOutputBlockParam =
            CodeExecutionOutputBlockParam.builder().fileId("file_id").build()

        assertThat(codeExecutionOutputBlockParam.fileId()).isEqualTo("file_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionOutputBlockParam =
            CodeExecutionOutputBlockParam.builder().fileId("file_id").build()

        val roundtrippedCodeExecutionOutputBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionOutputBlockParam),
                jacksonTypeRef<CodeExecutionOutputBlockParam>(),
            )

        assertThat(roundtrippedCodeExecutionOutputBlockParam)
            .isEqualTo(codeExecutionOutputBlockParam)
    }
}
