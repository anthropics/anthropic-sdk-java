// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EncryptedCodeExecutionResultBlockParamTest {

    @Test
    fun create() {
        val encryptedCodeExecutionResultBlockParam =
            EncryptedCodeExecutionResultBlockParam.builder()
                .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        assertThat(encryptedCodeExecutionResultBlockParam.content())
            .containsExactly(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
        assertThat(encryptedCodeExecutionResultBlockParam.encryptedStdout())
            .isEqualTo("encrypted_stdout")
        assertThat(encryptedCodeExecutionResultBlockParam.returnCode()).isEqualTo(0L)
        assertThat(encryptedCodeExecutionResultBlockParam.stderr()).isEqualTo("stderr")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val encryptedCodeExecutionResultBlockParam =
            EncryptedCodeExecutionResultBlockParam.builder()
                .addContent(CodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val roundtrippedEncryptedCodeExecutionResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(encryptedCodeExecutionResultBlockParam),
                jacksonTypeRef<EncryptedCodeExecutionResultBlockParam>(),
            )

        assertThat(roundtrippedEncryptedCodeExecutionResultBlockParam)
            .isEqualTo(encryptedCodeExecutionResultBlockParam)
    }
}
