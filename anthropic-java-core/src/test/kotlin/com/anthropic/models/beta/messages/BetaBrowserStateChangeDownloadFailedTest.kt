// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserStateChangeDownloadFailedTest {

    @Test
    fun create() {
        val betaBrowserStateChangeDownloadFailed =
            BetaBrowserStateChangeDownloadFailed.builder()
                .downloadId("download_id")
                .url("url")
                .error("error")
                .build()

        assertThat(betaBrowserStateChangeDownloadFailed.downloadId()).isEqualTo("download_id")
        assertThat(betaBrowserStateChangeDownloadFailed.url()).isEqualTo("url")
        assertThat(betaBrowserStateChangeDownloadFailed.error()).contains("error")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChangeDownloadFailed =
            BetaBrowserStateChangeDownloadFailed.builder()
                .downloadId("download_id")
                .url("url")
                .error("error")
                .build()

        val roundtrippedBetaBrowserStateChangeDownloadFailed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChangeDownloadFailed),
                jacksonTypeRef<BetaBrowserStateChangeDownloadFailed>(),
            )

        assertThat(roundtrippedBetaBrowserStateChangeDownloadFailed)
            .isEqualTo(betaBrowserStateChangeDownloadFailed)
    }
}
