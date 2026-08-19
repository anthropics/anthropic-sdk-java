// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserStateChangeDownloadFailedTest {

    @Test
    fun create() {
        val browserStateChangeDownloadFailed =
            BrowserStateChangeDownloadFailed.builder()
                .downloadId("download_id")
                .url("url")
                .error("error")
                .build()

        assertThat(browserStateChangeDownloadFailed.downloadId()).isEqualTo("download_id")
        assertThat(browserStateChangeDownloadFailed.url()).isEqualTo("url")
        assertThat(browserStateChangeDownloadFailed.error()).contains("error")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChangeDownloadFailed =
            BrowserStateChangeDownloadFailed.builder()
                .downloadId("download_id")
                .url("url")
                .error("error")
                .build()

        val roundtrippedBrowserStateChangeDownloadFailed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChangeDownloadFailed),
                jacksonTypeRef<BrowserStateChangeDownloadFailed>(),
            )

        assertThat(roundtrippedBrowserStateChangeDownloadFailed)
            .isEqualTo(browserStateChangeDownloadFailed)
    }
}
