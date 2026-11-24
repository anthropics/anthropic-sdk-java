package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekRegionNotConfiguredTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekRegionNotConfigured =
            BetaGoogleManagedProjectCmekRegionNotConfigured.of("effective_external_key_id")

        assertThat(betaGoogleManagedProjectCmekRegionNotConfigured.effectiveExternalKeyId())
            .contains("effective_external_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionNotConfigured =
            BetaGoogleManagedProjectCmekRegionNotConfigured.of("effective_external_key_id")

        val roundtrippedBetaGoogleManagedProjectCmekRegionNotConfigured =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionNotConfigured),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionNotConfigured>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionNotConfigured)
            .isEqualTo(betaGoogleManagedProjectCmekRegionNotConfigured)
    }
}
