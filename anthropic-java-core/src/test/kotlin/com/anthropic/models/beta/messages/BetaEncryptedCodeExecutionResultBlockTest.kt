// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEncryptedCodeExecutionResultBlockTest {

    @Test
    fun create() {
        val betaEncryptedCodeExecutionResultBlock =
            BetaEncryptedCodeExecutionResultBlock.builder()
                .addContent(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        assertThat(betaEncryptedCodeExecutionResultBlock.content())
            .containsExactly(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
        assertThat(betaEncryptedCodeExecutionResultBlock.encryptedStdout())
            .isEqualTo("encrypted_stdout")
        assertThat(betaEncryptedCodeExecutionResultBlock.returnCode()).isEqualTo(0L)
        assertThat(betaEncryptedCodeExecutionResultBlock.stderr()).isEqualTo("stderr")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEncryptedCodeExecutionResultBlock =
            BetaEncryptedCodeExecutionResultBlock.builder()
                .addContent(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val roundtrippedBetaEncryptedCodeExecutionResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEncryptedCodeExecutionResultBlock),
                jacksonTypeRef<BetaEncryptedCodeExecutionResultBlock>(),
            )

        assertThat(roundtrippedBetaEncryptedCodeExecutionResultBlock)
            .isEqualTo(betaEncryptedCodeExecutionResultBlock)
    }
}
