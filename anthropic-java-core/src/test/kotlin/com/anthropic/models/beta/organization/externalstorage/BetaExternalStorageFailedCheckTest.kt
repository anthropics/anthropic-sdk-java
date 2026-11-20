package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageFailedCheckTest {

    @Test
    fun create() {
        val betaExternalStorageFailedCheck =
            BetaExternalStorageFailedCheck.builder()
                .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                .message("message")
                .role(BetaExternalStorageFailedCheck.Role.INGEST)
                .build()

        assertThat(betaExternalStorageFailedCheck.check())
            .isEqualTo(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
        assertThat(betaExternalStorageFailedCheck.message()).isEqualTo("message")
        assertThat(betaExternalStorageFailedCheck.role())
            .contains(BetaExternalStorageFailedCheck.Role.INGEST)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorageFailedCheck =
            BetaExternalStorageFailedCheck.builder()
                .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                .message("message")
                .role(BetaExternalStorageFailedCheck.Role.INGEST)
                .build()

        val roundtrippedBetaExternalStorageFailedCheck =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorageFailedCheck),
                jacksonTypeRef<BetaExternalStorageFailedCheck>(),
            )

        assertThat(roundtrippedBetaExternalStorageFailedCheck)
            .isEqualTo(betaExternalStorageFailedCheck)
    }
}
