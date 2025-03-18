// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelRetrieveParamsTest {

    @Test
    fun create() {
        ModelRetrieveParams.builder().modelId("model_id").build()
    }

    @Test
    fun pathParams() {
        val params = ModelRetrieveParams.builder().modelId("model_id").build()

        assertThat(params._pathParam(0)).isEqualTo("model_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
