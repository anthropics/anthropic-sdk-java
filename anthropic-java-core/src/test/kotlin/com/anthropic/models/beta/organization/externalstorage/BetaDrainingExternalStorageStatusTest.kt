package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDrainingExternalStorageStatusTest {

    @Test
    fun create() {
        val betaDrainingExternalStorageStatus =
            BetaDrainingExternalStorageStatus.builder()
                .healthy(true)
                .lastCheckedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .removalStarted(true)
                .build()

        assertThat(betaDrainingExternalStorageStatus.healthy()).isEqualTo(true)
        assertThat(betaDrainingExternalStorageStatus.lastCheckedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaDrainingExternalStorageStatus.removalStarted()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDrainingExternalStorageStatus =
            BetaDrainingExternalStorageStatus.builder()
                .healthy(true)
                .lastCheckedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .removalStarted(true)
                .build()

        val roundtrippedBetaDrainingExternalStorageStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDrainingExternalStorageStatus),
                jacksonTypeRef<BetaDrainingExternalStorageStatus>(),
            )

        assertThat(roundtrippedBetaDrainingExternalStorageStatus)
            .isEqualTo(betaDrainingExternalStorageStatus)
    }
}
