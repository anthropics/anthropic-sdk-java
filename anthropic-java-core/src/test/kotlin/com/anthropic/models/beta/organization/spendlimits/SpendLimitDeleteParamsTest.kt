package com.anthropic.models.beta.organization.spendlimits

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SpendLimitDeleteParamsTest {

    @Test
    fun create() {
        SpendLimitDeleteParams.builder().spendLimitId("spend_limit_id").build()
    }

    @Test
    fun pathParams() {
        val params = SpendLimitDeleteParams.builder().spendLimitId("spend_limit_id").build()

        assertThat(params._pathParam(0)).isEqualTo("spend_limit_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
