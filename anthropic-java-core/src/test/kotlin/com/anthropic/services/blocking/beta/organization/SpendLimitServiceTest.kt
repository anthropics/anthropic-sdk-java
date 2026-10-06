package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.SpendLimitSetParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SpendLimitServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val spendLimitService = client.beta().organization().spendLimits()

        val betaSpendLimit = spendLimitService.retrieve("spend_limit_id")

        betaSpendLimit.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val spendLimitService = client.beta().organization().spendLimits()

        val page = spendLimitService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val spendLimitService = client.beta().organization().spendLimits()

        val spendLimit = spendLimitService.delete("spend_limit_id")

        spendLimit.validate()
    }

    @Test
    fun set() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val spendLimitService = client.beta().organization().spendLimits()

        val betaSpendLimit =
            spendLimitService.set(
                SpendLimitSetParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .amount("50000")
                    .userScope("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .period(BetaSpendLimitPeriod.DAILY)
                    .build()
            )

        betaSpendLimit.validate()
    }
}
