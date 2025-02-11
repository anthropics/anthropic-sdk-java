// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchExpiredResultTest {

    @Test
    fun createBetaMessageBatchExpiredResult() {
        val betaMessageBatchExpiredResult = BetaMessageBatchExpiredResult.builder().build()
        assertThat(betaMessageBatchExpiredResult).isNotNull
    }
}
