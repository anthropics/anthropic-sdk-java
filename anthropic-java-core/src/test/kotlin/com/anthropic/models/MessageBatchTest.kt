// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageBatchTest {

    @Test
    fun createMessageBatch() {
        val messageBatch =
            MessageBatch.builder()
                .id("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
                .archivedAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .cancelInitiatedAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .createdAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .endedAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .expiresAt(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
                .processingStatus(MessageBatch.ProcessingStatus.IN_PROGRESS)
                .requestCounts(
                    MessageBatchRequestCounts.builder()
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
        assertThat(messageBatch).isNotNull
        assertThat(messageBatch.id()).isEqualTo("msgbatch_013Zva2CMHLNnXjNJJKqJ2EF")
        assertThat(messageBatch.archivedAt())
            .contains(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(messageBatch.cancelInitiatedAt())
            .contains(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(messageBatch.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(messageBatch.endedAt())
            .contains(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(messageBatch.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2024-08-20T18:37:24.100435Z"))
        assertThat(messageBatch.processingStatus())
            .isEqualTo(MessageBatch.ProcessingStatus.IN_PROGRESS)
        assertThat(messageBatch.requestCounts())
            .isEqualTo(
                MessageBatchRequestCounts.builder()
                    .canceled(10L)
                    .errored(30L)
                    .expired(10L)
                    .processing(100L)
                    .succeeded(50L)
                    .build()
            )
        assertThat(messageBatch.resultsUrl())
            .contains(
                "https://api.anthropic.com/v1/messages/batches/msgbatch_013Zva2CMHLNnXjNJJKqJ2EF/results"
            )
    }
}
