// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolUseBlockTest {

    @Test
    fun create() {
        val toolUseBlock =
            ToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .toolsetName("toolset_name")
                .build()

        assertThat(toolUseBlock.id()).isEqualTo("id")
        assertThat(toolUseBlock.caller())
            .isEqualTo(ToolUseBlock.Caller.ofDirect(DirectCaller.builder().build()))
        assertThat(toolUseBlock.input())
            .isEqualTo(
                ToolUseBlock.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(toolUseBlock.name()).isEqualTo("x")
        assertThat(toolUseBlock.toolsetName()).contains("toolset_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolUseBlock =
            ToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(
                    ToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .toolsetName("toolset_name")
                .build()

        val roundtrippedToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolUseBlock),
                jacksonTypeRef<ToolUseBlock>(),
            )

        assertThat(roundtrippedToolUseBlock).isEqualTo(toolUseBlock)
    }
}
