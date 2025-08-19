// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServerToolUseBlockParamTest {

    @Test
    fun create() {
        val serverToolUseBlockParam =
            ServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(serverToolUseBlockParam.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(serverToolUseBlockParam._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(serverToolUseBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolUseBlockParam =
            ServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedServerToolUseBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolUseBlockParam),
                jacksonTypeRef<ServerToolUseBlockParam>(),
            )

        assertThat(roundtrippedServerToolUseBlockParam).isEqualTo(serverToolUseBlockParam)
    }
}
