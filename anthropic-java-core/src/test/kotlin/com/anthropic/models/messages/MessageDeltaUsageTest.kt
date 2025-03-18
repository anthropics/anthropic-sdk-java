// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageDeltaUsageTest {

    @Test
    fun create() {
        val messageDeltaUsage = MessageDeltaUsage.builder().outputTokens(503L).build()

        assertThat(messageDeltaUsage.outputTokens()).isEqualTo(503L)
    }
}
