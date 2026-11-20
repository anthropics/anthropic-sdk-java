package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAvailableExternalStorageStatusTest {

    @Test
    fun create() {
        val betaAvailableExternalStorageStatus =
            BetaAvailableExternalStorageStatus.builder()
                .healthy(true)
                .lastCheckedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(betaAvailableExternalStorageStatus.healthy()).isEqualTo(true)
        assertThat(betaAvailableExternalStorageStatus.lastCheckedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAvailableExternalStorageStatus =
            BetaAvailableExternalStorageStatus.builder()
                .healthy(true)
                .lastCheckedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedBetaAvailableExternalStorageStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAvailableExternalStorageStatus),
                jacksonTypeRef<BetaAvailableExternalStorageStatus>(),
            )

        assertThat(roundtrippedBetaAvailableExternalStorageStatus)
            .isEqualTo(betaAvailableExternalStorageStatus)
    }
}
