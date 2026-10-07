package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitSetParamsTest {

    @Test
    fun create() {
        SpendLimitSetParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .amount("50000")
            .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
            .period(BetaSpendLimitPeriod.DAILY)
            .build()
    }

    @Test
    fun headers() {
        val params =
            SpendLimitSetParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .amount("50000")
                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .period(BetaSpendLimitPeriod.DAILY)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("message-batches-2024-09-24").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SpendLimitSetParams.builder()
                .amount("50000")
                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SpendLimitSetParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .amount("50000")
                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .period(BetaSpendLimitPeriod.DAILY)
                .build()

        val body = params._body()

        assertThat(body.amount()).contains("50000")
        assertThat(body.scope())
            .isEqualTo(SpendLimitSetParams.Scope.ofUser("user_01WCz1FkmYMm4gnmykNKUu3Q"))
        assertThat(body.period()).contains(BetaSpendLimitPeriod.DAILY)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SpendLimitSetParams.builder()
                .amount("50000")
                .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .build()

        val body = params._body()

        assertThat(body.amount()).contains("50000")
        assertThat(body.scope())
            .isEqualTo(SpendLimitSetParams.Scope.ofUser("user_01WCz1FkmYMm4gnmykNKUu3Q"))
    }
}
