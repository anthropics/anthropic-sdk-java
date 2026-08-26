// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyDeleteParamsTest {

    @Test
    fun create() {
        ExternalKeyDeleteParams.builder().externalKeyId("external_key_id").build()
    }

    @Test
    fun pathParams() {
        val params = ExternalKeyDeleteParams.builder().externalKeyId("external_key_id").build()

        assertThat(params._pathParam(0)).isEqualTo("external_key_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
