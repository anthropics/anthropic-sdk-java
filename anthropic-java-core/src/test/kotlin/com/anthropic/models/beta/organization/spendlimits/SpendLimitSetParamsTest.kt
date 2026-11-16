package com.anthropic.models.beta.organization.spendlimits

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitSetParamsTest {

    @Test
    fun create() {
        SpendLimitSetParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .amount("50000")
            .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
            .period(BetaSpendLimitPeriod.DAILY)
            .build()
    }

    @Test
    fun headers() {
        val params =
            SpendLimitSetParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .amount("50000")
                .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .period(BetaSpendLimitPeriod.DAILY)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SpendLimitSetParams.builder()
                .amount("50000")
                .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            SpendLimitSetParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .amount("50000")
                .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .period(BetaSpendLimitPeriod.DAILY)
                .build()

        val body = params._body()

        assertThat(body.amount()).contains("50000")
        assertThat(body.scope())
            .isEqualTo(SpendLimitSetParams.Scope.ofUser("user_01WxYzAbCdEfGhJkMnPqRsTu"))
        assertThat(body.period()).contains(BetaSpendLimitPeriod.DAILY)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            SpendLimitSetParams.builder()
                .amount("50000")
                .userScope("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val body = params._body()

        assertThat(body.amount()).contains("50000")
        assertThat(body.scope())
            .isEqualTo(SpendLimitSetParams.Scope.ofUser("user_01WxYzAbCdEfGhJkMnPqRsTu"))
    }
}
