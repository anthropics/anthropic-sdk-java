package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekRegionPendingTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekRegionPending =
            BetaGoogleManagedProjectCmekRegionPending.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        assertThat(betaGoogleManagedProjectCmekRegionPending.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedProjectCmekRegionPending.error()).contains("error")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionPending =
            BetaGoogleManagedProjectCmekRegionPending.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val roundtrippedBetaGoogleManagedProjectCmekRegionPending =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionPending),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionPending>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionPending)
            .isEqualTo(betaGoogleManagedProjectCmekRegionPending)
    }
}
