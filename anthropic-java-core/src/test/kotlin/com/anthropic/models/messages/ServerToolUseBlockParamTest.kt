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
                .input(
                    ServerToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(ServerToolUseBlockParam.Name.WEB_SEARCH)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        assertThat(serverToolUseBlockParam.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(serverToolUseBlockParam.input())
            .isEqualTo(
                ServerToolUseBlockParam.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(serverToolUseBlockParam.name())
            .isEqualTo(ServerToolUseBlockParam.Name.WEB_SEARCH)
        assertThat(serverToolUseBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(serverToolUseBlockParam.caller())
            .contains(ServerToolUseBlockParam.Caller.ofDirect(DirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolUseBlockParam =
            ServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(
                    ServerToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(ServerToolUseBlockParam.Name.WEB_SEARCH)
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        val roundtrippedServerToolUseBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolUseBlockParam),
                jacksonTypeRef<ServerToolUseBlockParam>(),
            )

        assertThat(roundtrippedServerToolUseBlockParam).isEqualTo(serverToolUseBlockParam)
    }
}
