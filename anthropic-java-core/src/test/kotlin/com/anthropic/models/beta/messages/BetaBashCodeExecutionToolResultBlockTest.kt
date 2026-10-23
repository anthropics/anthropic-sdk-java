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
                    BetaBashCodeExecutionToolResultError.of(
                        BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .build()

        assertThat(betaBashCodeExecutionToolResultBlock.content())
            .isEqualTo(
                BetaBashCodeExecutionToolResultBlock.Content.ofBetaBashCodeExecutionToolResultError(
                    BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                )
            )
        assertThat(betaBashCodeExecutionToolResultBlock.toolUseId()).isEqualTo("srvtoolu_c")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionToolResultBlock =
            BetaBashCodeExecutionToolResultBlock.builder()
                .content(
                    BetaBashCodeExecutionToolResultError.of(
                        BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
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
