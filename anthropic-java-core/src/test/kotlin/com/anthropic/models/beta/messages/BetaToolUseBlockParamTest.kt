// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
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
}
