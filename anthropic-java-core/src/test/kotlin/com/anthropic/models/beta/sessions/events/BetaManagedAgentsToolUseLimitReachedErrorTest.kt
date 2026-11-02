package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsToolUseLimitReachedErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsToolUseLimitReachedError =
            BetaManagedAgentsToolUseLimitReachedError.builder()
                .message(
                    "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                )
                .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                .build()

        assertThat(betaManagedAgentsToolUseLimitReachedError.message())
            .isEqualTo(
                "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
            )
        assertThat(betaManagedAgentsToolUseLimitReachedError.retryStatus())
            .isEqualTo(
                BetaManagedAgentsToolUseLimitReachedError.RetryStatus.ofExhausted(
                    BetaManagedAgentsRetryStatusExhausted.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsToolUseLimitReachedError =
            BetaManagedAgentsToolUseLimitReachedError.builder()
                .message(
                    "This turn stopped because the agent loop reached its limit of 500 tool-use rounds since the turn started or last resumed. A round is one model response that calls tools, plus running those tools. Nothing failed. Send the session a new message to continue; nothing from the stopped turn is discarded."
                )
                .retryStatus(BetaManagedAgentsRetryStatusExhausted.builder().build())
                .build()

        val roundtrippedBetaManagedAgentsToolUseLimitReachedError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsToolUseLimitReachedError),
                jacksonTypeRef<BetaManagedAgentsToolUseLimitReachedError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsToolUseLimitReachedError)
            .isEqualTo(betaManagedAgentsToolUseLimitReachedError)
    }
}
