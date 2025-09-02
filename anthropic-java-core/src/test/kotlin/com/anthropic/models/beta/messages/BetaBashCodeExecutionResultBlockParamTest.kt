// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionResultBlockParamTest {

    @Test
    fun create() {
        val betaBashCodeExecutionResultBlockParam =
            BetaBashCodeExecutionResultBlockParam.builder()
                .addContent(
                    BetaBashCodeExecutionOutputBlockParam.builder().fileId("file_id").build()
                )
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(betaBashCodeExecutionResultBlockParam.content())
            .containsExactly(
                BetaBashCodeExecutionOutputBlockParam.builder().fileId("file_id").build()
            )
        assertThat(betaBashCodeExecutionResultBlockParam.returnCode()).isEqualTo(0L)
        assertThat(betaBashCodeExecutionResultBlockParam.stderr()).isEqualTo("stderr")
        assertThat(betaBashCodeExecutionResultBlockParam.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionResultBlockParam =
            BetaBashCodeExecutionResultBlockParam.builder()
                .addContent(
                    BetaBashCodeExecutionOutputBlockParam.builder().fileId("file_id").build()
                )
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedBetaBashCodeExecutionResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionResultBlockParam),
                jacksonTypeRef<BetaBashCodeExecutionResultBlockParam>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionResultBlockParam)
            .isEqualTo(betaBashCodeExecutionResultBlockParam)
    }
}
