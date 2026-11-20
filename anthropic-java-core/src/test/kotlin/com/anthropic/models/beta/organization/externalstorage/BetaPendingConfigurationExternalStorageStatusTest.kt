package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaPendingConfigurationExternalStorageStatusTest {

    @Test
    fun create() {
        val betaPendingConfigurationExternalStorageStatus =
            BetaPendingConfigurationExternalStorageStatus.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaPendingConfigurationExternalStorageStatus =
            BetaPendingConfigurationExternalStorageStatus.builder().build()

        val roundtrippedBetaPendingConfigurationExternalStorageStatus =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaPendingConfigurationExternalStorageStatus),
                jacksonTypeRef<BetaPendingConfigurationExternalStorageStatus>(),
            )

        assertThat(roundtrippedBetaPendingConfigurationExternalStorageStatus)
            .isEqualTo(betaPendingConfigurationExternalStorageStatus)
    }
}
