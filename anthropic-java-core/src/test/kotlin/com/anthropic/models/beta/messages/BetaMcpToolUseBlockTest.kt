// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolUseBlockTest {

    @Test
    fun create() {
        val betaMcpToolUseBlock =
            BetaMcpToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .build()

        assertThat(betaMcpToolUseBlock.id()).isEqualTo("id")
        assertThat(betaMcpToolUseBlock._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaMcpToolUseBlock.name()).isEqualTo("name")
        assertThat(betaMcpToolUseBlock.serverName()).isEqualTo("server_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolUseBlock =
            BetaMcpToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .build()

        val roundtrippedBetaMcpToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolUseBlock),
                jacksonTypeRef<BetaMcpToolUseBlock>(),
            )

        assertThat(roundtrippedBetaMcpToolUseBlock).isEqualTo(betaMcpToolUseBlock)
    }
}
