package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaStorageVerificationFailedErrorDetailsTest {

    @Test
    fun create() {
        val betaStorageVerificationFailedErrorDetails =
            BetaStorageVerificationFailedErrorDetails.builder()
                .addFailedCheck(
                    BetaExternalStorageFailedCheck.builder()
                        .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                        .message("message")
                        .role(BetaExternalStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .field("inference_data_privacy.safeguards_storage")
                .build()

        assertThat(betaStorageVerificationFailedErrorDetails.failedChecks())
            .containsExactly(
                BetaExternalStorageFailedCheck.builder()
                    .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                    .message("message")
                    .role(BetaExternalStorageFailedCheck.Role.INGEST)
                    .build()
            )
        assertThat(betaStorageVerificationFailedErrorDetails.field())
            .contains("inference_data_privacy.safeguards_storage")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaStorageVerificationFailedErrorDetails =
            BetaStorageVerificationFailedErrorDetails.builder()
                .addFailedCheck(
                    BetaExternalStorageFailedCheck.builder()
                        .check(BetaExternalStorageFailedCheck.Check.ALREADY_REGISTERED)
                        .message("message")
                        .role(BetaExternalStorageFailedCheck.Role.INGEST)
                        .build()
                )
                .field("inference_data_privacy.safeguards_storage")
                .build()

        val roundtrippedBetaStorageVerificationFailedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaStorageVerificationFailedErrorDetails),
                jacksonTypeRef<BetaStorageVerificationFailedErrorDetails>(),
            )

        assertThat(roundtrippedBetaStorageVerificationFailedErrorDetails)
            .isEqualTo(betaStorageVerificationFailedErrorDetails)
    }
}
