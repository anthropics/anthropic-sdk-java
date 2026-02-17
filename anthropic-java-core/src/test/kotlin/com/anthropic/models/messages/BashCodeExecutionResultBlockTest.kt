// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionResultBlockTest {

    @Test
    fun create() {
        val bashCodeExecutionResultBlock =
            BashCodeExecutionResultBlock.builder()
                .addContent(BashCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        assertThat(bashCodeExecutionResultBlock.content())
            .containsExactly(BashCodeExecutionOutputBlock.builder().fileId("file_id").build())
        assertThat(bashCodeExecutionResultBlock.returnCode()).isEqualTo(0L)
        assertThat(bashCodeExecutionResultBlock.stderr()).isEqualTo("stderr")
        assertThat(bashCodeExecutionResultBlock.stdout()).isEqualTo("stdout")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionResultBlock =
            BashCodeExecutionResultBlock.builder()
                .addContent(BashCodeExecutionOutputBlock.builder().fileId("file_id").build())
                .returnCode(0L)
                .stderr("stderr")
                .stdout("stdout")
                .build()

        val roundtrippedBashCodeExecutionResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionResultBlock),
                jacksonTypeRef<BashCodeExecutionResultBlock>(),
            )

        assertThat(roundtrippedBashCodeExecutionResultBlock).isEqualTo(bashCodeExecutionResultBlock)
    }
}
