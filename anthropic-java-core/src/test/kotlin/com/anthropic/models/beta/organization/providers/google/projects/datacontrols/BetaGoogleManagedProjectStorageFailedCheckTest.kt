package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectStorageFailedCheckTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectStorageFailedCheck =
            BetaGoogleManagedProjectStorageFailedCheck.builder()
                .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                .message("message")
                .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                .build()

        assertThat(betaGoogleManagedProjectStorageFailedCheck.check())
            .isEqualTo(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
        assertThat(betaGoogleManagedProjectStorageFailedCheck.message()).isEqualTo("message")
        assertThat(betaGoogleManagedProjectStorageFailedCheck.role())
            .contains(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectStorageFailedCheck =
            BetaGoogleManagedProjectStorageFailedCheck.builder()
                .check(BetaGoogleManagedProjectStorageFailedCheck.Check.BUCKET_SETTINGS)
                .message("message")
                .role(BetaGoogleManagedProjectStorageFailedCheck.Role.INGEST)
                .build()

        val roundtrippedBetaGoogleManagedProjectStorageFailedCheck =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectStorageFailedCheck),
                jacksonTypeRef<BetaGoogleManagedProjectStorageFailedCheck>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectStorageFailedCheck)
            .isEqualTo(betaGoogleManagedProjectStorageFailedCheck)
    }
}
