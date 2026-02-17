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
                .input(
                    ServerToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(ServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        assertThat(serverToolUseBlock.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(serverToolUseBlock.caller())
            .isEqualTo(ServerToolUseBlock.Caller.ofDirect(DirectCaller.builder().build()))
        assertThat(serverToolUseBlock.input())
            .isEqualTo(
                ServerToolUseBlock.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(serverToolUseBlock.name()).isEqualTo(ServerToolUseBlock.Name.WEB_SEARCH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolUseBlock =
            ServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .caller(DirectCaller.builder().build())
                .input(
                    ServerToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
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
