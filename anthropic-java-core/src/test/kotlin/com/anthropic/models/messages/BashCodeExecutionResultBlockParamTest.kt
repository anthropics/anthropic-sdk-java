// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionResultBlockParamTest {

    @Test
    fun create() {
        val bashCodeExecutionResultBlockParam =
            BashCodeExecutionResultBlockParam.builder()
                .addContent(BashCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(bashCodeExecutionResultBlockParam.content())
            .containsExactly(BashCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
        assertThat(bashCodeExecutionResultBlockParam.returnCode()).isEqualTo(0L)
        assertThat(bashCodeExecutionResultBlockParam.stderr()).isEqualTo("stderr")
        assertThat(bashCodeExecutionResultBlockParam.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionResultBlockParam =
            BashCodeExecutionResultBlockParam.builder()
                .addContent(BashCodeExecutionOutputBlockParam.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedBashCodeExecutionResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionResultBlockParam),
                jacksonTypeRef<BashCodeExecutionResultBlockParam>(),
            )

        assertThat(roundtrippedBashCodeExecutionResultBlockParam)
            .isEqualTo(bashCodeExecutionResultBlockParam)
    }
}
