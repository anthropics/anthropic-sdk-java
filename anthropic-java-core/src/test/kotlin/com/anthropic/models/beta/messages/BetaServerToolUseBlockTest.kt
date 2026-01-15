// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolUseBlockTest {

    @Test
    fun create() {
        val betaServerToolUseBlock =
            BetaServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaServerToolUseBlock.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaServerToolUseBlock._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaServerToolUseBlock.name()).isEqualTo(BetaServerToolUseBlock.Name.WEB_SEARCH)
        assertThat(betaServerToolUseBlock.caller())
            .contains(BetaServerToolUseBlock.Caller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolUseBlock =
            BetaServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaServerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolUseBlock),
                jacksonTypeRef<BetaServerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaServerToolUseBlock).isEqualTo(betaServerToolUseBlock)
    }
}
