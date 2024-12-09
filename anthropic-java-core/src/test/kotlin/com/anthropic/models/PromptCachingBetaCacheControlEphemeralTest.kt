// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PromptCachingBetaCacheControlEphemeralTest {

    @Test
    fun createPromptCachingBetaCacheControlEphemeral() {
        val promptCachingBetaCacheControlEphemeral =
            PromptCachingBetaCacheControlEphemeral.builder()
                .type(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
                .build()
        assertThat(promptCachingBetaCacheControlEphemeral).isNotNull
        assertThat(promptCachingBetaCacheControlEphemeral.type())
            .isEqualTo(PromptCachingBetaCacheControlEphemeral.Type.EPHEMERAL)
    }
}
