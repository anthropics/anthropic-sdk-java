// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserStateChangeDownloadCompletedTest {

    @Test
    fun create() {
        val betaBrowserStateChangeDownloadCompleted =
            BetaBrowserStateChangeDownloadCompleted.builder()
                .downloadId("download_id")
                .url("url")
                .path("path")
                .sizeBytes(0L)
                .build()

        assertThat(betaBrowserStateChangeDownloadCompleted.downloadId()).isEqualTo("download_id")
        assertThat(betaBrowserStateChangeDownloadCompleted.url()).isEqualTo("url")
        assertThat(betaBrowserStateChangeDownloadCompleted.path()).contains("path")
        assertThat(betaBrowserStateChangeDownloadCompleted.sizeBytes()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChangeDownloadCompleted =
            BetaBrowserStateChangeDownloadCompleted.builder()
                .downloadId("download_id")
                .url("url")
                .path("path")
                .sizeBytes(0L)
                .build()

        val roundtrippedBetaBrowserStateChangeDownloadCompleted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChangeDownloadCompleted),
                jacksonTypeRef<BetaBrowserStateChangeDownloadCompleted>(),
            )

        assertThat(roundtrippedBetaBrowserStateChangeDownloadCompleted)
            .isEqualTo(betaBrowserStateChangeDownloadCompleted)
    }
}
