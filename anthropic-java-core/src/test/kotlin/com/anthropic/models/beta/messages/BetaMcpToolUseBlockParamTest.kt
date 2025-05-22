// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMcpToolUseBlockParamTest {

    @Test
    fun create() {
        val betaMcpToolUseBlockParam =
            BetaMcpToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaMcpToolUseBlockParam.id()).isEqualTo("id")
        assertThat(betaMcpToolUseBlockParam._input())
            .isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaMcpToolUseBlockParam.name()).isEqualTo("name")
        assertThat(betaMcpToolUseBlockParam.serverName()).isEqualTo("server_name")
        assertThat(betaMcpToolUseBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMcpToolUseBlockParam =
            BetaMcpToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaMcpToolUseBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMcpToolUseBlockParam),
                jacksonTypeRef<BetaMcpToolUseBlockParam>(),
            )

        assertThat(roundtrippedBetaMcpToolUseBlockParam).isEqualTo(betaMcpToolUseBlockParam)
    }
}
