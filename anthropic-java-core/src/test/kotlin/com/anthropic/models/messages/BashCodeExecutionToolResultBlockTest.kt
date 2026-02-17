// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionToolResultBlockTest {

    @Test
    fun create() {
        val bashCodeExecutionToolResultBlock =
            BashCodeExecutionToolResultBlock.builder()
                .content(
                    BashCodeExecutionToolResultError.builder()
                        .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(bashCodeExecutionToolResultBlock.content())
            .isEqualTo(
                BashCodeExecutionToolResultBlock.Content.ofBashCodeExecutionToolResultError(
                    BashCodeExecutionToolResultError.builder()
                        .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(bashCodeExecutionToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultBlock =
            BashCodeExecutionToolResultBlock.builder()
                .content(
                    BashCodeExecutionToolResultError.builder()
                        .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBashCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultBlock),
                jacksonTypeRef<BashCodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedBashCodeExecutionToolResultBlock)
            .isEqualTo(bashCodeExecutionToolResultBlock)
    }
}
