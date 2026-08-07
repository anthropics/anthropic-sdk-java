// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaEncryptedCodeExecutionResultBlockParamTest {

    @Test
    fun create() {
        val betaEncryptedCodeExecutionResultBlockParam =
            BetaEncryptedCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.of("file_id"))
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        assertThat(betaEncryptedCodeExecutionResultBlockParam.content())
            .containsExactly(BetaCodeExecutionOutputBlockParam.of("file_id"))
        assertThat(betaEncryptedCodeExecutionResultBlockParam.encryptedStdout())
            .isEqualTo("encrypted_stdout")
        assertThat(betaEncryptedCodeExecutionResultBlockParam.returnCode()).isEqualTo(0L)
        assertThat(betaEncryptedCodeExecutionResultBlockParam.stderr()).isEqualTo("stderr")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaEncryptedCodeExecutionResultBlockParam =
            BetaEncryptedCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.of("file_id"))
                .encryptedStdout("encrypted_stdout")
                .returnCode(0L)
                .stderr("stderr")
                .build()

        val roundtrippedBetaEncryptedCodeExecutionResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaEncryptedCodeExecutionResultBlockParam),
                jacksonTypeRef<BetaEncryptedCodeExecutionResultBlockParam>(),
            )

        assertThat(roundtrippedBetaEncryptedCodeExecutionResultBlockParam)
            .isEqualTo(betaEncryptedCodeExecutionResultBlockParam)
    }
}
