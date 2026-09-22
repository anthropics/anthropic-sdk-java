package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolParamTest {

    @Test
    fun create() {
        val betaMcpToolParam =
            BetaMcpToolParam.builder()
                .inputSchema(
                    BetaMcpToolParam.InputSchema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .description("description")
                .build()

        assertThat(betaMcpToolParam.inputSchema())
            .isEqualTo(
                BetaMcpToolParam.InputSchema.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaMcpToolParam.name()).isEqualTo("x")
        assertThat(betaMcpToolParam.description()).contains("description")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolParam =
            BetaMcpToolParam.builder()
                .inputSchema(
                    BetaMcpToolParam.InputSchema.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .description("description")
                .build()

        val roundtrippedBetaMcpToolParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolParam),
                jacksonTypeRef<BetaMcpToolParam>(),
            )

        assertThat(roundtrippedBetaMcpToolParam).isEqualTo(betaMcpToolParam)
    }
}
