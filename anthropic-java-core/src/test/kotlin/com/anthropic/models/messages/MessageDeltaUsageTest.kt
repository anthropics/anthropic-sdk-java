// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageDeltaUsageTest {

    @Test
    fun create() {
        val messageDeltaUsage = MessageDeltaUsage.builder().outputTokens(503L).build()

        assertThat(messageDeltaUsage.outputTokens()).isEqualTo(503L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageDeltaUsage = MessageDeltaUsage.builder().outputTokens(503L).build()

        val roundtrippedMessageDeltaUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageDeltaUsage),
                jacksonTypeRef<MessageDeltaUsage>(),
            )

        assertThat(roundtrippedMessageDeltaUsage).isEqualTo(messageDeltaUsage)
    }
}
