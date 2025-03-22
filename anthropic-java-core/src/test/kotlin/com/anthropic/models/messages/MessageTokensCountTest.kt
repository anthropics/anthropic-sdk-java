// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageTokensCountTest {

    @Test
    fun create() {
        val messageTokensCount = MessageTokensCount.builder().inputTokens(2095L).build()

        assertThat(messageTokensCount.inputTokens()).isEqualTo(2095L)
    }
}
