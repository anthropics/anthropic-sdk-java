// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageDeltaUsageTest {

    @Test
    fun createMessageDeltaUsage() {
        val messageDeltaUsage = MessageDeltaUsage.builder().outputTokens(503L).build()
        assertThat(messageDeltaUsage).isNotNull
        assertThat(messageDeltaUsage.outputTokens()).isEqualTo(503L)
    }
}
