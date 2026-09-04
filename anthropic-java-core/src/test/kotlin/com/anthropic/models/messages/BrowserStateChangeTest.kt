// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BrowserStateChangeTest {

    @Test
    fun ofTabOpened() {
        val tabOpened = BrowserStateChangeTabOpened.of("tab_id")

        val browserStateChange = BrowserStateChange.ofTabOpened(tabOpened)

        assertThat(browserStateChange.tabOpened()).contains(tabOpened)
        assertThat(browserStateChange.downloadStarted()).isEmpty
        assertThat(browserStateChange.downloadCompleted()).isEmpty
        assertThat(browserStateChange.downloadFailed()).isEmpty
    }

    @Test
    fun ofTabOpenedRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChange =
            BrowserStateChange.ofTabOpened(BrowserStateChangeTabOpened.of("tab_id"))

        val roundtrippedBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChange),
                jacksonTypeRef<BrowserStateChange>(),
            )

        assertThat(roundtrippedBrowserStateChange).isEqualTo(browserStateChange)
    }

    @Test
    fun ofDownloadStarted() {
        val downloadStarted =
            BrowserStateChangeDownloadStarted.builder().downloadId("download_id").url("url").build()

        val browserStateChange = BrowserStateChange.ofDownloadStarted(downloadStarted)

        assertThat(browserStateChange.tabOpened()).isEmpty
        assertThat(browserStateChange.downloadStarted()).contains(downloadStarted)
        assertThat(browserStateChange.downloadCompleted()).isEmpty
        assertThat(browserStateChange.downloadFailed()).isEmpty
    }

    @Test
    fun ofDownloadStartedRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChange =
            BrowserStateChange.ofDownloadStarted(
                BrowserStateChangeDownloadStarted.builder()
                    .downloadId("download_id")
                    .url("url")
                    .build()
            )

        val roundtrippedBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChange),
                jacksonTypeRef<BrowserStateChange>(),
            )

        assertThat(roundtrippedBrowserStateChange).isEqualTo(browserStateChange)
    }

    @Test
    fun ofDownloadCompleted() {
        val downloadCompleted =
            BrowserStateChangeDownloadCompleted.builder()
                .downloadId("download_id")
                .url("url")
                .path("path")
                .sizeBytes(0L)
                .build()

        val browserStateChange = BrowserStateChange.ofDownloadCompleted(downloadCompleted)

        assertThat(browserStateChange.tabOpened()).isEmpty
        assertThat(browserStateChange.downloadStarted()).isEmpty
        assertThat(browserStateChange.downloadCompleted()).contains(downloadCompleted)
        assertThat(browserStateChange.downloadFailed()).isEmpty
    }

    @Test
    fun ofDownloadCompletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChange =
            BrowserStateChange.ofDownloadCompleted(
                BrowserStateChangeDownloadCompleted.builder()
                    .downloadId("download_id")
                    .url("url")
                    .path("path")
                    .sizeBytes(0L)
                    .build()
            )

        val roundtrippedBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChange),
                jacksonTypeRef<BrowserStateChange>(),
            )

        assertThat(roundtrippedBrowserStateChange).isEqualTo(browserStateChange)
    }

    @Test
    fun ofDownloadFailed() {
        val downloadFailed =
            BrowserStateChangeDownloadFailed.builder()
                .downloadId("download_id")
                .url("url")
                .error("error")
                .build()

        val browserStateChange = BrowserStateChange.ofDownloadFailed(downloadFailed)

        assertThat(browserStateChange.tabOpened()).isEmpty
        assertThat(browserStateChange.downloadStarted()).isEmpty
        assertThat(browserStateChange.downloadCompleted()).isEmpty
        assertThat(browserStateChange.downloadFailed()).contains(downloadFailed)
    }

    @Test
    fun ofDownloadFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChange =
            BrowserStateChange.ofDownloadFailed(
                BrowserStateChangeDownloadFailed.builder()
                    .downloadId("download_id")
                    .url("url")
                    .error("error")
                    .build()
            )

        val roundtrippedBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChange),
                jacksonTypeRef<BrowserStateChange>(),
            )

        assertThat(roundtrippedBrowserStateChange).isEqualTo(browserStateChange)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val browserStateChange =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "download_id" to "download_id",
                            "url" to "url",
                        )
                    ),
                    jacksonTypeRef<BrowserStateChange>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { browserStateChange.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(browserStateChange.downloadId()).contains("download_id")
        assertThat(browserStateChange.url()).contains("url")

        val mismatchedBrowserStateChange =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "download_id" to listOf("invalid"),
                            "url" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BrowserStateChange>(),
                )

        assertThat(mismatchedBrowserStateChange.downloadId()).isEmpty
        assertThat(mismatchedBrowserStateChange.url()).isEmpty
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val browserStateChange =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BrowserStateChange>())

        val e = assertThrows<AnthropicInvalidDataException> { browserStateChange.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(browserStateChange.downloadId()).isEmpty
        assertThat(browserStateChange.url()).isEmpty
    }
}
