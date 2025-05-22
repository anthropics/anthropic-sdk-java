// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionResultBlockParamTest {

    @Test
    fun create() {
        val betaCodeExecutionResultBlockParam =
            BetaCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(betaCodeExecutionResultBlockParam.content())
            .containsExactly(BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
        assertThat(betaCodeExecutionResultBlockParam.returnCode()).isEqualTo(0L)
        assertThat(betaCodeExecutionResultBlockParam.stderr()).isEqualTo("stderr")
        assertThat(betaCodeExecutionResultBlockParam.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionResultBlockParam =
            BetaCodeExecutionResultBlockParam.builder()
                .addContent(BetaCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedBetaCodeExecutionResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionResultBlockParam),
                jacksonTypeRef<BetaCodeExecutionResultBlockParam>(),
            )

        assertThat(roundtrippedBetaCodeExecutionResultBlockParam)
            .isEqualTo(betaCodeExecutionResultBlockParam)
    }
}
