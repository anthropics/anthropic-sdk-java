package com.anthropic.services.blocking.beta.organization.spendlimits

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.spendlimits.BetaSpendLimitPeriod
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestApproveParams
import com.anthropic.models.beta.organization.spendlimits.increaserequests.IncreaseRequestDenyParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class IncreaseRequestServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val increaseRequestService = client.beta().organization().spendLimits().increaseRequests()

        val betaSpendLimitIncreaseRequest =
            increaseRequestService.retrieve("spend_limit_increase_request_id")

        betaSpendLimitIncreaseRequest.validate()
    }

    @Test
    fun list() {
        val increaseRequestService = client.beta().organization().spendLimits().increaseRequests()

        val page = increaseRequestService.list()

        page.response().validate()
    }

    @Test
    fun approve() {
        val increaseRequestService = client.beta().organization().spendLimits().increaseRequests()

        val response =
            increaseRequestService.approve(
                IncreaseRequestApproveParams.builder()
                    .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                    .amount("50000")
                    .period(BetaSpendLimitPeriod.DAILY)
                    .suppressNotification(true)
                    .build()
            )

        response.validate()
    }

    @Test
    fun deny() {
        val increaseRequestService = client.beta().organization().spendLimits().increaseRequests()

        val betaSpendLimitIncreaseRequest =
            increaseRequestService.deny(
                IncreaseRequestDenyParams.builder()
                    .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                    .suppressNotification(true)
                    .build()
            )

        betaSpendLimitIncreaseRequest.validate()
    }
}
