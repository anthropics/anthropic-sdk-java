// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyRetrieveParamsTest {

    @Test
    fun create() {
        ApiKeyRetrieveParams.builder().apiKeyId("api_key_id").build()
    }

    @Test
    fun pathParams() {
        val params = ApiKeyRetrieveParams.builder().apiKeyId("api_key_id").build()

        assertThat(params._pathParam(0)).isEqualTo("api_key_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
