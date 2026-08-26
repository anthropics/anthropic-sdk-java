// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserRemoveParamsTest {

    @Test
    fun create() {
        UserRemoveParams.builder().userId("user_id").build()
    }

    @Test
    fun pathParams() {
        val params = UserRemoveParams.builder().userId("user_id").build()

        assertThat(params._pathParam(0)).isEqualTo("user_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
