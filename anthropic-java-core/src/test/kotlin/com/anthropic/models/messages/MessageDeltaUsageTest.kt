// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MessageDeltaUsageTest {

    @Test
    fun create() {
        val messageDeltaUsage =
            MessageDeltaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(
                    ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .build()

        assertThat(messageDeltaUsage.cacheCreationInputTokens()).contains(2051L)
        assertThat(messageDeltaUsage.cacheReadInputTokens()).contains(2051L)
        assertThat(messageDeltaUsage.inputTokens()).contains(2095L)
        assertThat(messageDeltaUsage.outputTokens()).isEqualTo(503L)
        assertThat(messageDeltaUsage.serverToolUse())
            .contains(ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val messageDeltaUsage =
            MessageDeltaUsage.builder()
                .cacheCreationInputTokens(2051L)
                .cacheReadInputTokens(2051L)
                .inputTokens(2095L)
                .outputTokens(503L)
                .serverToolUse(
                    ServerToolUsage.builder().webFetchRequests(2L).webSearchRequests(0L).build()
                )
                .build()

        val roundtrippedMessageDeltaUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(messageDeltaUsage),
                jacksonTypeRef<MessageDeltaUsage>(),
            )

        assertThat(roundtrippedMessageDeltaUsage).isEqualTo(messageDeltaUsage)
    }
}
