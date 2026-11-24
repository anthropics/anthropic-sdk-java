package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekRegionSuspendedTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekRegionSuspended =
            BetaGoogleManagedProjectCmekRegionSuspended.of("effective_external_key_id")

        assertThat(betaGoogleManagedProjectCmekRegionSuspended.effectiveExternalKeyId())
            .contains("effective_external_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionSuspended =
            BetaGoogleManagedProjectCmekRegionSuspended.of("effective_external_key_id")

        val roundtrippedBetaGoogleManagedProjectCmekRegionSuspended =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionSuspended),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionSuspended>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionSuspended)
            .isEqualTo(betaGoogleManagedProjectCmekRegionSuspended)
    }
}
