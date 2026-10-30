package com.anthropic.models.organization.spendlimits.increaserequests

import com.anthropic.models.organization.spendlimits.SpendLimitPeriod
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncreaseRequestApproveParamsTest {

    @Test
    fun create() {
        IncreaseRequestApproveParams.builder()
            .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
            .amount("50000")
            .period(SpendLimitPeriod.DAILY)
            .suppressNotification(true)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            IncreaseRequestApproveParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .amount("50000")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("spend_limit_increase_request_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            IncreaseRequestApproveParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .amount("50000")
                .period(SpendLimitPeriod.DAILY)
                .suppressNotification(true)
                .build()

        val body = params._body()

        assertThat(body.amount()).isEqualTo("50000")
        assertThat(body.period()).contains(SpendLimitPeriod.DAILY)
        assertThat(body.suppressNotification()).contains(true)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            IncreaseRequestApproveParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .amount("50000")
                .build()

        val body = params._body()

        assertThat(body.amount()).isEqualTo("50000")
    }
}
