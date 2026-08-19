// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolUseBlockParamTest {

    @Test
    fun create() {
        val betaToolUseBlockParam =
            BetaToolUseBlockParam.builder()
                .id("id")
                .input(
                    BetaToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .toolsetName("toolset_name")
                .build()

        assertThat(betaToolUseBlockParam.id()).isEqualTo("id")
        assertThat(betaToolUseBlockParam.input())
            .isEqualTo(
                BetaToolUseBlockParam.Input.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolUseBlockParam.name()).isEqualTo("x")
        assertThat(betaToolUseBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolUseBlockParam.caller())
            .contains(BetaToolUseBlockParam.Caller.ofDirect(BetaDirectCaller.builder().build()))
        assertThat(betaToolUseBlockParam.toolsetName()).contains("toolset_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseBlockParam =
            BetaToolUseBlockParam.builder()
                .id("id")
                .input(
                    BetaToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .name("x")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .toolsetName("toolset_name")
                .build()

        val roundtrippedBetaToolUseBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseBlockParam),
                jacksonTypeRef<BetaToolUseBlockParam>(),
            )

        assertThat(roundtrippedBetaToolUseBlockParam).isEqualTo(betaToolUseBlockParam)
    }
}
