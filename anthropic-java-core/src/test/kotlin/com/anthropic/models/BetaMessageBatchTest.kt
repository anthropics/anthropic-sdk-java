// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetaMessageBatchTest {

    @Test
    fun createBetaMessageBatch() {
        val betaMessageBatch =
            BetaMessageBatch.builder()
                .id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
                .archivedAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .cancelInitiatedAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .createdAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .endedAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .expiresAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .processingStatus(BetaMessageBatch.ProcessingStatus.IN_PROGRESS)
                .requestCounts(
                    BetaMessageBatchRequestCounts.builder()
                        .canceled(10L)
                        .errored(30L)
                        .expired(10L)
                        .processing(100L)
                        .succeeded(50L)
                        .build()
                )
                .resultsUrl(
                    "https://api.anthropic.com/v1/messages/batches/msgbatch_013Zva2CMHLNnXjNJJKqJ2EF/results"
                )
                .build()
        assertThat(betaMessageBatch).isNotNull
        assertThat(betaMessageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(betaMessageBatch.archivedAt())
            .contains(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(betaMessageBatch.cancelInitiatedAt())
            .contains(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(betaMessageBatch.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(betaMessageBatch.endedAt())
            .contains(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(betaMessageBatch.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(betaMessageBatch.processingStatus())
            .isEqualTo(BetaMessageBatch.ProcessingStatus.IN_PROGRESS)
        assertThat(betaMessageBatch.requestCounts())
            .isEqualTo(
                BetaMessageBatchRequestCounts.builder()
                    .canceled(10L)
                    .errored(30L)
                    .expired(10L)
                    .processing(100L)
                    .succeeded(50L)
                    .build()
            )
        assertThat(betaMessageBatch.resultsUrl())
            .contains(
                "https://api.anthropic.com/v1/messages/batches/msgbatch_013Zva2CMHLNnXjNJJKqJ2EF/results"
            )
    }
}
