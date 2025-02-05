// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchDeleteParamsTest {

    @Test
    fun create() {
        MessageBatchDeleteParams.builder().messageBatchId("message_batch_id").build()
    }

    @Test
    fun getPathParam() {
        val params = MessageBatchDeleteParams.builder().messageBatchId("message_batch_id").build()
        assertThat(params).isNotNull
        // path param "messageBatchId"
        assertThat(params.getPathParam(0)).isEqualTo("message_batch_id")
        // out-of-bound path param
        assertThat(params.getPathParam(1)).isEqualTo("")
    }
}
