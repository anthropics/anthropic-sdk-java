// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages.batches

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BatchListPageResponseTest {

    @Test
    fun create() {
        val batchListPageResponse =
            BatchListPageResponse.builder()
                .addData(
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(batchListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(batchListPageResponse.firstId()).contains("first_id")
        assertThat(batchListPageResponse.hasMore()).isEqualTo(true)
        assertThat(batchListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val batchListPageResponse =
            BatchListPageResponse.builder()
                .addData(
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedBatchListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(batchListPageResponse),
                jacksonTypeRef<BatchListPageResponse>(),
            )

        assertThat(roundtrippedBatchListPageResponse).isEqualTo(batchListPageResponse)
    }
}
