// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserStateChangeDownloadStartedTest {

    @Test
    fun create() {
        val betaBrowserStateChangeDownloadStarted =
            BetaBrowserStateChangeDownloadStarted.builder()
                .downloadId("download_id")
                .url("url")
                .build()

        assertThat(betaBrowserStateChangeDownloadStarted.downloadId()).isEqualTo("download_id")
        assertThat(betaBrowserStateChangeDownloadStarted.url()).isEqualTo("url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChangeDownloadStarted =
            BetaBrowserStateChangeDownloadStarted.builder()
                .downloadId("download_id")
                .url("url")
                .build()

        val roundtrippedBetaBrowserStateChangeDownloadStarted =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChangeDownloadStarted),
                jacksonTypeRef<BetaBrowserStateChangeDownloadStarted>(),
            )

        assertThat(roundtrippedBetaBrowserStateChangeDownloadStarted)
            .isEqualTo(betaBrowserStateChangeDownloadStarted)
    }
}
