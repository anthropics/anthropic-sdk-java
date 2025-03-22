// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
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
                .build()

        assertThat(betaToolUseBlock.id()).isEqualTo("id")
        assertThat(betaToolUseBlock._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaToolUseBlock.name()).isEqualTo("x")
    }
}
