// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserStateChangeDownloadCompletedTest {

    @Test
    fun create() {
        val browserStateChangeDownloadCompleted =
            BrowserStateChangeDownloadCompleted.builder()
                .downloadId("download_id")
                .url("url")
                .path("path")
                .sizeBytes(0L)
                .build()

        assertThat(browserStateChangeDownloadCompleted.downloadId()).isEqualTo("download_id")
        assertThat(browserStateChangeDownloadCompleted.url()).isEqualTo("url")
        assertThat(browserStateChangeDownloadCompleted.path()).contains("path")
        assertThat(browserStateChangeDownloadCompleted.sizeBytes()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChangeDownloadCompleted =
            BrowserStateChangeDownloadCompleted.builder()
                .downloadId("download_id")
                .url("url")
                .path("path")
                .sizeBytes(0L)
                .build()

        val roundtrippedBrowserStateChangeDownloadCompleted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChangeDownloadCompleted),
                jacksonTypeRef<BrowserStateChangeDownloadCompleted>(),
            )

        assertThat(roundtrippedBrowserStateChangeDownloadCompleted)
            .isEqualTo(browserStateChangeDownloadCompleted)
    }
}
