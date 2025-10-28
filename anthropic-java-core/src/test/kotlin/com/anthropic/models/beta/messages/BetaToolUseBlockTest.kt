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
                .input(
                    BetaToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .build()

        assertThat(betaToolUseBlock.id()).isEqualTo("id")
        assertThat(betaToolUseBlock.input())
            .isEqualTo(
                BetaToolUseBlock.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolUseBlock.name()).isEqualTo("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseBlock =
            BetaToolUseBlock.builder()
                .id("id")
                .input(
                    BetaToolUseBlock.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .build()

        val roundtrippedBetaToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseBlock),
                jacksonTypeRef<BetaToolUseBlock>(),
            )

        assertThat(roundtrippedBetaToolUseBlock).isEqualTo(betaToolUseBlock)
    }
}
