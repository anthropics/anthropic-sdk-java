package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.SpendLimitSetParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SpendLimitServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val spendLimitServiceAsync =
            client.beta().organization().spendLimits().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaSpendLimitFuture = spendLimitServiceAsync.retrieve("spend_limit_id")

        val betaSpendLimit = betaSpendLimitFuture.get()
        betaSpendLimit.validate()
    }

    @Test
    fun list() {
        val spendLimitServiceAsync =
            client.beta().organization().spendLimits().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = spendLimitServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val spendLimitServiceAsync =
            client.beta().organization().spendLimits().withOptions {
                it.putQueryParam("beta", "true")
            }

        val spendLimitFuture = spendLimitServiceAsync.delete("spend_limit_id")

        val spendLimit = spendLimitFuture.get()
        spendLimit.validate()
    }

    @Test
    fun set() {
        val spendLimitServiceAsync =
            client.beta().organization().spendLimits().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaSpendLimitFuture =
            spendLimitServiceAsync.set(
                SpendLimitSetParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .amount("50000")
                    .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .period(BetaSpendLimitPeriod.DAILY)
                    .build()
            )

        val betaSpendLimit = betaSpendLimitFuture.get()
        betaSpendLimit.validate()
    }
}
