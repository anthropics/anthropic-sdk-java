// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolResultBlockTest {

    @Test
    fun create() {
        val betaMcpToolResultBlock =
            BetaMcpToolResultBlock.builder()
                .content("string")
                .isError(true)
                .toolUseId("tool_use_id")
                .build()

        assertThat(betaMcpToolResultBlock.content())
            .isEqualTo(BetaMcpToolResultBlock.Content.ofString("string"))
        assertThat(betaMcpToolResultBlock.isError()).isEqualTo(true)
        assertThat(betaMcpToolResultBlock.toolUseId()).isEqualTo("tool_use_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolResultBlock =
            BetaMcpToolResultBlock.builder()
                .content("string")
                .isError(true)
                .toolUseId("tool_use_id")
                .build()

        val roundtrippedBetaMcpToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolResultBlock),
                jacksonTypeRef<BetaMcpToolResultBlock>(),
            )

        assertThat(roundtrippedBetaMcpToolResultBlock).isEqualTo(betaMcpToolResultBlock)
    }
}
