package com.anthropic.models.beta.organization.claudecode.managedsettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaSettingsDocumentLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val betaSettingsDocumentLimitExceededErrorDetails =
            BetaSettingsDocumentLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaSettingsDocumentLimitExceededErrorDetails =
            BetaSettingsDocumentLimitExceededErrorDetails.builder().build()

        val roundtrippedBetaSettingsDocumentLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaSettingsDocumentLimitExceededErrorDetails),
                jacksonTypeRef<BetaSettingsDocumentLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedBetaSettingsDocumentLimitExceededErrorDetails)
            .isEqualTo(betaSettingsDocumentLimitExceededErrorDetails)
    }
}
