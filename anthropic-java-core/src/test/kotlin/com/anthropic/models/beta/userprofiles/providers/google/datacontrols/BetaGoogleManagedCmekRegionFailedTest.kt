package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekRegionFailedTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekRegionFailed =
            BetaGoogleManagedCmekRegionFailed.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        assertThat(betaGoogleManagedCmekRegionFailed.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedCmekRegionFailed.error()).isEqualTo("error")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionFailed =
            BetaGoogleManagedCmekRegionFailed.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .build()

        val roundtrippedBetaGoogleManagedCmekRegionFailed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionFailed),
                jacksonTypeRef<BetaGoogleManagedCmekRegionFailed>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionFailed)
            .isEqualTo(betaGoogleManagedCmekRegionFailed)
    }
}
