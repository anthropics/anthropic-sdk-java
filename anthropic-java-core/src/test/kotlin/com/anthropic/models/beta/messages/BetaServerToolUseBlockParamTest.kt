// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolUseBlockParamTest {

    @Test
    fun create() {
        val betaServerToolUseBlockParam =
            BetaServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(BetaServerToolUseBlockParam.Name.WEB_SEARCH)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaServerToolUseBlockParam.id()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaServerToolUseBlockParam._input())
            .isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaServerToolUseBlockParam.name())
            .isEqualTo(BetaServerToolUseBlockParam.Name.WEB_SEARCH)
        assertThat(betaServerToolUseBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolUseBlockParam =
            BetaServerToolUseBlockParam.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(BetaServerToolUseBlockParam.Name.WEB_SEARCH)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaServerToolUseBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolUseBlockParam),
                jacksonTypeRef<BetaServerToolUseBlockParam>(),
            )

        assertThat(roundtrippedBetaServerToolUseBlockParam).isEqualTo(betaServerToolUseBlockParam)
    }
}
