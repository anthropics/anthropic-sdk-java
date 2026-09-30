package com.anthropic.models.organization.serviceaccounts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountArchiveParamsTest {

    @Test
    fun create() {
        ServiceAccountArchiveParams.builder().serviceAccountId("service_account_id").build()
    }

    @Test
    fun pathParams() {
        val params =
            ServiceAccountArchiveParams.builder().serviceAccountId("service_account_id").build()

        assertThat(params._pathParam(0)).isEqualTo("service_account_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
