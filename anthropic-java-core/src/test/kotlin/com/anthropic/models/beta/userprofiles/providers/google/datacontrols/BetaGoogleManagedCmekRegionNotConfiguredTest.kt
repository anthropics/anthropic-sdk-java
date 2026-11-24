package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekRegionNotConfiguredTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekRegionNotConfigured =
            BetaGoogleManagedCmekRegionNotConfigured.of("effective_external_key_id")

        assertThat(betaGoogleManagedCmekRegionNotConfigured.effectiveExternalKeyId())
            .contains("effective_external_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionNotConfigured =
            BetaGoogleManagedCmekRegionNotConfigured.of("effective_external_key_id")

        val roundtrippedBetaGoogleManagedCmekRegionNotConfigured =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionNotConfigured),
                jacksonTypeRef<BetaGoogleManagedCmekRegionNotConfigured>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionNotConfigured)
            .isEqualTo(betaGoogleManagedCmekRegionNotConfigured)
    }
}
