// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServerToolUseBlockTest {

    @Test
    fun create() {
        val serverToolUseBlock =
            ServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .caller(DirectCaller.builder().build())
                .input(JsonValue.from(mapOf("foo" to "bar")))
                .name(ServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        assertThat(serverToolUseBlock.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(serverToolUseBlock.caller())
            .isEqualTo(ServerToolUseBlock.Caller.ofDirect(DirectCaller.builder().build()))
        assertThat(serverToolUseBlock._input()).isEqualTo(JsonValue.from(mapOf("foo" to "bar")))
        assertThat(serverToolUseBlock.name()).isEqualTo(ServerToolUseBlock.Name.WEB_SEARCH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolUseBlock =
            ServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .caller(DirectCaller.builder().build())
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(ServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        val roundtrippedServerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolUseBlock),
                jacksonTypeRef<ServerToolUseBlock>(),
            )

        assertThat(roundtrippedServerToolUseBlock).isEqualTo(serverToolUseBlock)
    }
}
