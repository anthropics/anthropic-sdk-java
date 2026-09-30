package com.anthropic.models.beta.organization.spendlimits.increaserequests

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IncreaseRequestRetrieveParamsTest {

    @Test
    fun create() {
        IncreaseRequestRetrieveParams.builder()
            .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            IncreaseRequestRetrieveParams.builder()
                .spendLimitIncreaseRequestId("spend_limit_increase_request_id")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("spend_limit_increase_request_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
