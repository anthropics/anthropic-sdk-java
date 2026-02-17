// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EncryptedCodeExecutionResultBlockTest {

    @Test
    fun create() {
        val encryptedCodeExecutionResultBlock =
            EncryptedCodeExecutionResultBlock.builder()
                .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        assertThat(encryptedCodeExecutionResultBlock.content())
            .containsExactly(CodeExecutionOutputBlock.builder().fileId("file_id").build())
        assertThat(encryptedCodeExecutionResultBlock.encryptedStdout())
            .isEqualTo("encrypted_stdout")
        assertThat(encryptedCodeExecutionResultBlock.returnCode()).isEqualTo(0L)
        assertThat(encryptedCodeExecutionResultBlock.stderr()).isEqualTo("stderr")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val encryptedCodeExecutionResultBlock =
            EncryptedCodeExecutionResultBlock.builder()
                .addContent(CodeExecutionOutputBlock.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val roundtrippedEncryptedCodeExecutionResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(encryptedCodeExecutionResultBlock),
                jacksonTypeRef<EncryptedCodeExecutionResultBlock>(),
            )

        assertThat(roundtrippedEncryptedCodeExecutionResultBlock)
            .isEqualTo(encryptedCodeExecutionResultBlock)
    }
}
