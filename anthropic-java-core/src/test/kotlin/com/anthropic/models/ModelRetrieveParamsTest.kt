// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ModelRetrieveParamsTest {

    @Test
    fun create() {
        ModelRetrieveParams.builder().modelId("model_id").build()
    }

    @Test
    fun getPathParam() {
        val params = ModelRetrieveParams.builder().modelId("model_id").build()
        assertThat(params).isNotNull
        // path param "modelId"
        assertThat(params.getPathParam(0)).isEqualTo("model_id")
        // out-of-bound path param
        assertThat(params.getPathParam(1)).isEqualTo("")
    }
}
