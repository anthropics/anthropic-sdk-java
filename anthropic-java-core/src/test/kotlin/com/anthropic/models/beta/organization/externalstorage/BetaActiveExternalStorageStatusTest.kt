package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaActiveExternalStorageStatusTest {

    @Test
    fun create() {
        val betaActiveExternalStorageStatus =
            BetaActiveExternalStorageStatus.builder()
                .healthy(true)
                .lastCheckedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(betaActiveExternalStorageStatus.healthy()).isEqualTo(true)
        assertThat(betaActiveExternalStorageStatus.lastCheckedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaActiveExternalStorageStatus =
            BetaActiveExternalStorageStatus.builder()
                .healthy(true)
                .lastCheckedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedBetaActiveExternalStorageStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaActiveExternalStorageStatus),
                jacksonTypeRef<BetaActiveExternalStorageStatus>(),
            )

        assertThat(roundtrippedBetaActiveExternalStorageStatus)
            .isEqualTo(betaActiveExternalStorageStatus)
    }
}
