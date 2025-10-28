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
                .input(
                    BetaServerToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        assertThat(betaServerToolUseBlock.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaServerToolUseBlock.input())
            .isEqualTo(
                BetaServerToolUseBlock.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaServerToolUseBlock.name()).isEqualTo(BetaServerToolUseBlock.Name.WEB_SEARCH)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolUseBlock =
            BetaServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(
                    BetaServerToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        val roundtrippedBetaServerToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolUseBlock),
                jacksonTypeRef<BetaServerToolUseBlock>(),
            )

        assertThat(roundtrippedBetaServerToolUseBlock).isEqualTo(betaServerToolUseBlock)
    }
}
