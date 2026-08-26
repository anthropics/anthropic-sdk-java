// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyUpdateParamsTest {

    @Test
    fun create() {
        ApiKeyUpdateParams.builder()
            .apiKeyId("api_key_id")
            .name("x")
            .status(ApiKeyUpdateParams.Status.ACTIVE)
            .build()
    }

    @Test
    fun pathParams() {
        val params = ApiKeyUpdateParams.builder().apiKeyId("api_key_id").build()

        assertThat(params._pathParam(0)).isEqualTo("api_key_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            ApiKeyUpdateParams.builder()
                .apiKeyId("api_key_id")
                .name("x")
                .status(ApiKeyUpdateParams.Status.ACTIVE)
                .build()

        val body = params._body()

        assertThat(body.name()).contains("x")
        assertThat(body.status()).contains(ApiKeyUpdateParams.Status.ACTIVE)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ApiKeyUpdateParams.builder().apiKeyId("api_key_id").build()

        val body = params._body()
    }
}
