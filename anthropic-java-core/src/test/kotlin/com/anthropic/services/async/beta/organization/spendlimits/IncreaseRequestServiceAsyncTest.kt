package com.anthropic.services.async.beta.organization.spendlimits

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestApproveParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestDenyParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class IncreaseRequestServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val increaseRequestServiceAsync =
            client.beta().organization().spendLimits().increaseRequests()

        val betaSpendLimitIncreaseRequestFuture =
            increaseRequestServiceAsync.retrieve("spend_limit_increase_request_id")

        val betaSpendLimitIncreaseRequest = betaSpendLimitIncreaseRequestFuture.get()
        betaSpendLimitIncreaseRequest.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val increaseRequestServiceAsync =
            client.beta().organization().spendLimits().increaseRequests()

        val pageFuture = increaseRequestServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun approve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val increaseRequestServiceAsync =
            client.beta().organization().spendLimits().increaseRequests()

        val responseFuture =
            increaseRequestServiceAsync.approve(
                IncreaseRequestApproveParams.builder()
                    .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                    .amount("50000")
                    .period(BetaSpendLimitPeriod.DAILY)
                    .suppressNotification(true)
                    .build()
            )

        val response = responseFuture.get()
        response.validate()
    }

    @Test
    fun deny() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val increaseRequestServiceAsync =
            client.beta().organization().spendLimits().increaseRequests()

        val betaSpendLimitIncreaseRequestFuture =
            increaseRequestServiceAsync.deny(
                IncreaseRequestDenyParams.builder()
                    .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                    .suppressNotification(true)
                    .build()
            )

        val betaSpendLimitIncreaseRequest = betaSpendLimitIncreaseRequestFuture.get()
        betaSpendLimitIncreaseRequest.validate()
    }
}
