package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolListingBlockTest {

    @Test
    fun create() {
        val betaMcpToolListingBlock =
            BetaMcpToolListingBlock.builder()
                .mcpServerName("mcp_server_name")
                .addTool(
                    BetaMcpTool.builder()
                        .inputSchema(
                            BetaMcpTool.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("name")
                        .description("description")
                        .build()
                )
                .build()

        assertThat(betaMcpToolListingBlock.mcpServerName()).isEqualTo("mcp_server_name")
        assertThat(betaMcpToolListingBlock.tools())
            .containsExactly(
                BetaMcpTool.builder()
                    .inputSchema(
                        BetaMcpTool.InputSchema.builder()
                            .putAdditionalProperty("foo", JsonValue.from("bar"))
                            .build()
                    )
                    .name("name")
                    .description("description")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolListingBlock =
            BetaMcpToolListingBlock.builder()
                .mcpServerName("mcp_server_name")
                .addTool(
                    BetaMcpTool.builder()
                        .inputSchema(
                            BetaMcpTool.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("name")
                        .description("description")
                        .build()
                )
                .build()

        val roundtrippedBetaMcpToolListingBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolListingBlock),
                jacksonTypeRef<BetaMcpToolListingBlock>(),
            )

        assertThat(roundtrippedBetaMcpToolListingBlock).isEqualTo(betaMcpToolListingBlock)
    }
}
