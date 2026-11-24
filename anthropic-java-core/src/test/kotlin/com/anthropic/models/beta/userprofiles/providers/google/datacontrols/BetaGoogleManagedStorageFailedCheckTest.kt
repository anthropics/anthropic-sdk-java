package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedStorageFailedCheckTest {

    @Test
    fun create() {
        val betaGoogleManagedStorageFailedCheck =
            BetaGoogleManagedStorageFailedCheck.builder()
                .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                .message("message")
                .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                .build()

        assertThat(betaGoogleManagedStorageFailedCheck.check())
            .isEqualTo(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
        assertThat(betaGoogleManagedStorageFailedCheck.message()).isEqualTo("message")
        assertThat(betaGoogleManagedStorageFailedCheck.role())
            .contains(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedStorageFailedCheck =
            BetaGoogleManagedStorageFailedCheck.builder()
                .check(BetaGoogleManagedStorageFailedCheck.Check.LABEL_MISMATCH)
                .message("message")
                .role(BetaGoogleManagedStorageFailedCheck.Role.INGEST)
                .build()

        val roundtrippedBetaGoogleManagedStorageFailedCheck =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedStorageFailedCheck),
                jacksonTypeRef<BetaGoogleManagedStorageFailedCheck>(),
            )

        assertThat(roundtrippedBetaGoogleManagedStorageFailedCheck)
            .isEqualTo(betaGoogleManagedStorageFailedCheck)
    }
}
