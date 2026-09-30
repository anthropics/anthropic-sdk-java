package com.anthropic.models.beta.organization.spendlimits.increaserequests

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncreaseRequestDenyParamsTest {

    @Test
    fun create() {
        IncreaseRequestDenyParams.builder()
            .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
            .suppressNotification(true)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            IncreaseRequestDenyParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("spend_limit_increase_request_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            IncreaseRequestDenyParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .suppressNotification(true)
                .build()

        val body = params._body()

        assertThat(body.suppressNotification()).contains(true)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            IncreaseRequestDenyParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .build()

        val body = params._body()
    }
}
