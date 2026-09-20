package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolTest {

    @Test
    fun create() {
        val betaMcpTool =
            BetaMcpTool.builder()
                .inputSchema(
                    BetaMcpTool.InputSchema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .description("description")
                .build()

        assertThat(betaMcpTool.inputSchema())
            .isEqualTo(
                BetaMcpTool.InputSchema.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaMcpTool.name()).isEqualTo("name")
        assertThat(betaMcpTool.description()).contains("description")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpTool =
            BetaMcpTool.builder()
                .inputSchema(
                    BetaMcpTool.InputSchema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("name")
                .description("description")
                .build()

        val roundtrippedBetaMcpTool =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpTool),
                jacksonTypeRef<BetaMcpTool>(),
            )

        assertThat(roundtrippedBetaMcpTool).isEqualTo(betaMcpTool)
    }
}
