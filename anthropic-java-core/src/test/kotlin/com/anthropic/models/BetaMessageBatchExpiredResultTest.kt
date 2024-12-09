// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchExpiredResultTest {

    @Test
    fun createBetaMessageBatchExpiredResult() {
        val betaMessageBatchExpiredResult =
            BetaMessageBatchExpiredResult.builder()
                .type(BetaMessageBatchExpiredResult.Type.EXPIRED)
                .build()
        assertThat(betaMessageBatchExpiredResult).isNotNull
        assertThat(betaMessageBatchExpiredResult.type())
            .isEqualTo(BetaMessageBatchExpiredResult.Type.EXPIRED)
    }
}
