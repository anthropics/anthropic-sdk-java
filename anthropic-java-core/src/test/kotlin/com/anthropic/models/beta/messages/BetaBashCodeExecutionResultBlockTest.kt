// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionResultBlockTest {

    @Test
    fun create() {
        val betaBashCodeExecutionResultBlock =
            BetaBashCodeExecutionResultBlock.builder()
                .addContent(BetaBashCodeExecutionOutputBlock.of("file_id"))
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(betaBashCodeExecutionResultBlock.content())
            .containsExactly(BetaBashCodeExecutionOutputBlock.of("file_id"))
        assertThat(betaBashCodeExecutionResultBlock.returnCode()).isEqualTo(0L)
        assertThat(betaBashCodeExecutionResultBlock.stderr()).isEqualTo("stderr")
        assertThat(betaBashCodeExecutionResultBlock.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionResultBlock =
            BetaBashCodeExecutionResultBlock.builder()
                .addContent(BetaBashCodeExecutionOutputBlock.of("file_id"))
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedBetaBashCodeExecutionResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionResultBlock),
                jacksonTypeRef<BetaBashCodeExecutionResultBlock>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionResultBlock)
            .isEqualTo(betaBashCodeExecutionResultBlock)
    }
}
