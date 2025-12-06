// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolUseBlockTest {

    @Test
    fun create() {
        val betaToolUseBlock =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaToolUseBlock.id()).isEqualTo("id")
        assertThat(betaToolUseBlock._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaToolUseBlock.name()).isEqualTo("x")
        assertThat(betaToolUseBlock.caller())
            .contains(BetaToolUseBlock.Caller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseBlock =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseBlock),
                jacksonTypeRef<BetaToolUseBlock>(),
            )

        assertThat(roundtrippedBetaToolUseBlock).isEqualTo(betaToolUseBlock)
    }
}
