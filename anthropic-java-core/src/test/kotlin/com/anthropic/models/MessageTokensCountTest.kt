// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageTokensCountTest {

    @Test
    fun createMessageTokensCount() {
        val messageTokensCount = MessageTokensCount.builder().inputTokens(2095L).build()
        assertThat(messageTokensCount).isNotNull
        assertThat(messageTokensCount.inputTokens()).isEqualTo(2095L)
    }
}
