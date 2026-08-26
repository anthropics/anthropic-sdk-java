// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserUpdateParamsTest {

    @Test
    fun create() {
        UserUpdateParams.builder().userId("user_id").role(UserUpdateParams.Role.USER).build()
    }

    @Test
    fun pathParams() {
        val params =
            UserUpdateParams.builder().userId("user_id").role(UserUpdateParams.Role.USER).build()

        assertThat(params._pathParam(0)).isEqualTo("user_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            UserUpdateParams.builder().userId("user_id").role(UserUpdateParams.Role.USER).build()

        val body = params._body()

        assertThat(body.role()).isEqualTo(UserUpdateParams.Role.USER)
    }
}
