package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolListingBlockParamTest {

    @Test
    fun create() {
        val betaMcpToolListingBlockParam =
            BetaMcpToolListingBlockParam.builder()
                .mcpServerName("x")
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        assertThat(betaMcpToolListingBlockParam.mcpServerName()).isEqualTo("x")
        assertThat(betaMcpToolListingBlockParam.tools())
            .containsExactly(
                BetaMcpToolParam.builder()
                    .inputSchema(
                        BetaMcpToolParam.InputSchema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("x")
                    .description("description")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolListingBlockParam =
            BetaMcpToolListingBlockParam.builder()
                .mcpServerName("x")
                .addTool(
                    BetaMcpToolParam.builder()
                        .inputSchema(
                            BetaMcpToolParam.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("x")
                        .description("description")
                        .build()
                )
                .build()

        val roundtrippedBetaMcpToolListingBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolListingBlockParam),
                jacksonTypeRef<BetaMcpToolListingBlockParam>(),
            )

        assertThat(roundtrippedBetaMcpToolListingBlockParam).isEqualTo(betaMcpToolListingBlockParam)
    }
}
