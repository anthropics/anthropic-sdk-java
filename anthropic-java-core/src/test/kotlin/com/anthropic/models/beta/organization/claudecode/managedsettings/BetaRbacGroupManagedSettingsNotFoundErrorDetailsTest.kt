package com.anthropic.models.beta.organization.claudecode.managedsettings

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacGroupManagedSettingsNotFoundErrorDetailsTest {

    @Test
    fun create() {
        val betaRbacGroupManagedSettingsNotFoundErrorDetails =
            BetaRbacGroupManagedSettingsNotFoundErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacGroupManagedSettingsNotFoundErrorDetails =
            BetaRbacGroupManagedSettingsNotFoundErrorDetails.builder().build()

        val roundtrippedBetaRbacGroupManagedSettingsNotFoundErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacGroupManagedSettingsNotFoundErrorDetails),
                jacksonTypeRef<BetaRbacGroupManagedSettingsNotFoundErrorDetails>(),
            )

        assertThat(roundtrippedBetaRbacGroupManagedSettingsNotFoundErrorDetails)
            .isEqualTo(betaRbacGroupManagedSettingsNotFoundErrorDetails)
    }
}
