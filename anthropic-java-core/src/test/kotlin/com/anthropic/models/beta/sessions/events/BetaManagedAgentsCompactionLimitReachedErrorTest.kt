package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsCompactionLimitReachedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsCompactionLimitReachedError =
            BetaManagedAgentsCompactionLimitReachedError.builder()
                .message(
                    "This turn stopped because the context was compacted the maximum number of times in a row without the model producing a message or a tool call. Nothing failed. This can happen when content sent with every request, such as a large system prompt, leaves too little room for the conversation. Send the session a new message to continue; nothing from the stopped turn is discarded."
                )
                .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                .build()

        assertThat(betaManagedAgentsCompactionLimitReachedError.message())
            .isEqualTo(
                "This turn stopped because the context was compacted the maximum number of times in a row without the model producing a message or a tool call. Nothing failed. This can happen when content sent with every request, such as a large system prompt, leaves too little room for the conversation. Send the session a new message to continue; nothing from the stopped turn is discarded."
            )
        assertThat(betaManagedAgentsCompactionLimitReachedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsCompactionLimitReachedError.RetryStatus.ofExhausted(
                    BetaManagedAgentsRetryStatusExhausted.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsCompactionLimitReachedError =
            BetaManagedAgentsCompactionLimitReachedError.builder()
                .message(
                    "This turn stopped because the context was compacted the maximum number of times in a row without the model producing a message or a tool call. Nothing failed. This can happen when content sent with every request, such as a large system prompt, leaves too little room for the conversation. Send the session a new message to continue; nothing from the stopped turn is discarded."
                )
                .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsCompactionLimitReachedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsCompactionLimitReachedError),
                jacksonTypeRef<BetaManagedAgentsCompactionLimitReachedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsCompactionLimitReachedError)
            .isEqualTo(betaManagedAgentsCompactionLimitReachedError)
    }
}
