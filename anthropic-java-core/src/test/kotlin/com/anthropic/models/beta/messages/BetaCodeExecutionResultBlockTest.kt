// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionResultBlockTest {

    @Test
    fun create() {
        val betaCodeExecutionResultBlock =
            BetaCodeExecutionResultBlock.builder()
                .addContent(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(betaCodeExecutionResultBlock.content())
            .containsExactly(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
        assertThat(betaCodeExecutionResultBlock.returnCode()).isEqualTo(0L)
        assertThat(betaCodeExecutionResultBlock.stderr()).isEqualTo("stderr")
        assertThat(betaCodeExecutionResultBlock.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionResultBlock =
            BetaCodeExecutionResultBlock.builder()
                .addContent(BetaCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedBetaCodeExecutionResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionResultBlock),
                jacksonTypeRef<BetaCodeExecutionResultBlock>(),
            )

        assertThat(roundtrippedBetaCodeExecutionResultBlock).isEqualTo(betaCodeExecutionResultBlock)
    }
}
