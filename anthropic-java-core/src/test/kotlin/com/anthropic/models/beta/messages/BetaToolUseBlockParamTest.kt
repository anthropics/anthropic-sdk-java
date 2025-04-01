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
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        assertThat(betaToolUseBlockParam.id()).isEqualTo("id")
        assertThat(betaToolUseBlockParam._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaToolUseBlockParam.name()).isEqualTo("name")
        assertThat(betaToolUseBlockParam.cacheControl())
            .contains(BetaCacheControlEphemeral.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseBlockParam =
            BetaToolUseBlockParam.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .cacheControl(BetaCacheControlEphemeral.builder().build())
                .build()

        val roundtrippedBetaToolUseBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseBlockParam),
                jacksonTypeRef<BetaToolUseBlockParam>(),
            )

        assertThat(roundtrippedBetaToolUseBlockParam).isEqualTo(betaToolUseBlockParam)
    }
}
