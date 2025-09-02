// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionToolResultBlockTest {

    @Test
    fun create() {
        val betaBashCodeExecutionToolResultBlock =
            BetaBashCodeExecutionToolResultBlock.builder()
                .content(
                    BetaBashCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(betaBashCodeExecutionToolResultBlock.content())
            .isEqualTo(
                BetaBashCodeExecutionToolResultBlock.Content.ofBetaBashCodeExecutionToolResultError(
                    BetaBashCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
            )
        assertThat(betaBashCodeExecutionToolResultBlock.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionToolResultBlock =
            BetaBashCodeExecutionToolResultBlock.builder()
                .content(
                    BetaBashCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBetaBashCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionToolResultBlock),
                jacksonTypeRef<BetaBashCodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionToolResultBlock)
            .isEqualTo(betaBashCodeExecutionToolResultBlock)
    }
}
