// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserStateChangeDownloadStartedTest {

    @Test
    fun create() {
        val browserStateChangeDownloadStarted =
            BrowserStateChangeDownloadStarted.builder().downloadId("download_id").url("url").build()

        assertThat(browserStateChangeDownloadStarted.downloadId()).isEqualTo("download_id")
        assertThat(browserStateChangeDownloadStarted.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChangeDownloadStarted =
            BrowserStateChangeDownloadStarted.builder().downloadId("download_id").url("url").build()

        val roundtrippedBrowserStateChangeDownloadStarted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChangeDownloadStarted),
                jacksonTypeRef<BrowserStateChangeDownloadStarted>(),
            )

        assertThat(roundtrippedBrowserStateChangeDownloadStarted)
            .isEqualTo(browserStateChangeDownloadStarted)
    }
}
