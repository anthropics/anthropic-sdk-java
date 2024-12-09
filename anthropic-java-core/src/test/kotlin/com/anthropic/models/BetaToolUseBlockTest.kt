// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.JsonValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaToolUseBlockTest {

    @Test
    fun createBetaToolUseBlock() {
        val betaToolUseBlock =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .type(BetaToolUseBlock.Type.TOOL_USE)
                .build()
        assertThat(betaToolUseBlock).isNotNull
        assertThat(betaToolUseBlock.id()).isEqualTo("id")
        assertThat(betaToolUseBlock._input()).isEqualTo(JsonValue.from(mapOf<String, Any>()))
        assertThat(betaToolUseBlock.name()).isEqualTo("x")
        assertThat(betaToolUseBlock.type()).isEqualTo(BetaToolUseBlock.Type.TOOL_USE)
    }
}
