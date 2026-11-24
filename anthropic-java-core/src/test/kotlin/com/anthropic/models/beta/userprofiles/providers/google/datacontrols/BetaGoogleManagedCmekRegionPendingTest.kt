package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekRegionPendingTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekRegionPending =
            BetaGoogleManagedCmekRegionPending.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        assertThat(betaGoogleManagedCmekRegionPending.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedCmekRegionPending.error()).contains("error")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionPending =
            BetaGoogleManagedCmekRegionPending.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val roundtrippedBetaGoogleManagedCmekRegionPending =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionPending),
                jacksonTypeRef<BetaGoogleManagedCmekRegionPending>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionPending)
            .isEqualTo(betaGoogleManagedCmekRegionPending)
    }
}
