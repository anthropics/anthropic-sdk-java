package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekRegionFailedTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekRegionFailed =
            BetaGoogleManagedProjectCmekRegionFailed.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        assertThat(betaGoogleManagedProjectCmekRegionFailed.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedProjectCmekRegionFailed.error()).isEqualTo("error")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionFailed =
            BetaGoogleManagedProjectCmekRegionFailed.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val roundtrippedBetaGoogleManagedProjectCmekRegionFailed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionFailed),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionFailed>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionFailed)
            .isEqualTo(betaGoogleManagedProjectCmekRegionFailed)
    }
}
