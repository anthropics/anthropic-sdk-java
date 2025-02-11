// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchExpiredResultTest {

    @Test
    fun createMessageBatchExpiredResult() {
        val messageBatchExpiredResult = MessageBatchExpiredResult.builder().build()
        assertThat(messageBatchExpiredResult).isNotNull
    }
}
