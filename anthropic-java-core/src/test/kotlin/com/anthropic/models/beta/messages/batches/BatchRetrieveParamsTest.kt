// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BatchRetrieveParamsTest {

    @Test
    fun create() {
        BatchRetrieveParams.builder()
            .messageBatchId("message_batch_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun getPathParam() {
        val params = BatchRetrieveParams.builder().messageBatchId("message_batch_id").build()
        assertThat(params).isNotNull
        // path param "messageBatchId"
        assertThat(params.getPathParam(0)).isEqualTo("message_batch_id")
        // out-of-bound path param
        assertThat(params.getPathParam(1)).isEqualTo("")
    }
}
