package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaBrowserStateChangeTest {

    @Test
    fun ofTabOpened() {
        val tabOpened = BetaBrowserStateChangeTabOpened.of("tab_id")

        val betaBrowserStateChange = BetaBrowserStateChange.ofTabOpened(tabOpened)

        assertThat(presentVariants(betaBrowserStateChange))
            .isEqualTo(mapOf("tabOpened" to Optional.of(tabOpened)))
    }

    @Test
    fun ofTabOpenedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChange =
            BetaBrowserStateChange.ofTabOpened(BetaBrowserStateChangeTabOpened.of("tab_id"))

        val roundtrippedBetaBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChange),
                jacksonTypeRef<BetaBrowserStateChange>(),
            )

        assertThat(roundtrippedBetaBrowserStateChange).isEqualTo(betaBrowserStateChange)
    }

    @Test
    fun ofDownloadStarted() {
        val downloadStarted =
            BetaBrowserStateChangeDownloadStarted.builder()
                .downloadId("download_id")
                .url("url")
                .build()

        val betaBrowserStateChange = BetaBrowserStateChange.ofDownloadStarted(downloadStarted)

        assertThat(presentVariants(betaBrowserStateChange))
            .isEqualTo(mapOf("downloadStarted" to Optional.of(downloadStarted)))
    }

    @Test
    fun ofDownloadStartedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChange =
            BetaBrowserStateChange.ofDownloadStarted(
                BetaBrowserStateChangeDownloadStarted.builder()
                    .downloadId("download_id")
                    .url("url")
                    .build()
            )

        val roundtrippedBetaBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChange),
                jacksonTypeRef<BetaBrowserStateChange>(),
            )

        assertThat(roundtrippedBetaBrowserStateChange).isEqualTo(betaBrowserStateChange)
    }

    @Test
    fun ofDownloadCompleted() {
        val downloadCompleted =
            BetaBrowserStateChangeDownloadCompleted.builder()
                .downloadId("download_id")
                .url("url")
                .path("path")
                .sizeBytes(0L)
                .build()

        val betaBrowserStateChange = BetaBrowserStateChange.ofDownloadCompleted(downloadCompleted)

        assertThat(presentVariants(betaBrowserStateChange))
            .isEqualTo(mapOf("downloadCompleted" to Optional.of(downloadCompleted)))
    }

    @Test
    fun ofDownloadCompletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChange =
            BetaBrowserStateChange.ofDownloadCompleted(
                BetaBrowserStateChangeDownloadCompleted.builder()
                    .downloadId("download_id")
                    .url("url")
                    .path("path")
                    .sizeBytes(0L)
                    .build()
            )

        val roundtrippedBetaBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChange),
                jacksonTypeRef<BetaBrowserStateChange>(),
            )

        assertThat(roundtrippedBetaBrowserStateChange).isEqualTo(betaBrowserStateChange)
    }

    @Test
    fun ofDownloadFailed() {
        val downloadFailed =
            BetaBrowserStateChangeDownloadFailed.builder()
                .downloadId("download_id")
                .url("url")
                .error("error")
                .build()

        val betaBrowserStateChange = BetaBrowserStateChange.ofDownloadFailed(downloadFailed)

        assertThat(presentVariants(betaBrowserStateChange))
            .isEqualTo(mapOf("downloadFailed" to Optional.of(downloadFailed)))
    }

    @Test
    fun ofDownloadFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateChange =
            BetaBrowserStateChange.ofDownloadFailed(
                BetaBrowserStateChangeDownloadFailed.builder()
                    .downloadId("download_id")
                    .url("url")
                    .error("error")
                    .build()
            )

        val roundtrippedBetaBrowserStateChange =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateChange),
                jacksonTypeRef<BetaBrowserStateChange>(),
            )

        assertThat(roundtrippedBetaBrowserStateChange).isEqualTo(betaBrowserStateChange)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaBrowserStateChange =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "download_id" to "download_id",
                            "url" to "url",
                        )
                    ),
                    jacksonTypeRef<BetaBrowserStateChange>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaBrowserStateChange.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaBrowserStateChange.downloadId()).contains("download_id")
        assertThat(betaBrowserStateChange.url()).contains("url")

        val mismatchedBetaBrowserStateChange =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "download_id" to listOf("invalid"),
                            "url" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaBrowserStateChange>(),
                )

        assertThat(mismatchedBetaBrowserStateChange.downloadId()).isEmpty
        assertThat(mismatchedBetaBrowserStateChange.url()).isEmpty
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
        val betaBrowserStateChange =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaBrowserStateChange>())

        val e = assertThrows<AnthropicInvalidDataException> { betaBrowserStateChange.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaBrowserStateChange.downloadId()).isEmpty
        assertThat(betaBrowserStateChange.url()).isEmpty
    }

    private fun presentVariants(
        betaBrowserStateChange: BetaBrowserStateChange
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "tabOpened" to betaBrowserStateChange.tabOpened(),
                "downloadStarted" to betaBrowserStateChange.downloadStarted(),
                "downloadCompleted" to betaBrowserStateChange.downloadCompleted(),
                "downloadFailed" to betaBrowserStateChange.downloadFailed(),
            )
            .filterValues { it.isPresent }
}
