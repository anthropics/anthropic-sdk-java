package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekRegionSuspendedTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekRegionSuspended =
            BetaGoogleManagedCmekRegionSuspended.of("effective_external_key_id")

        assertThat(betaGoogleManagedCmekRegionSuspended.effectiveExternalKeyId())
            .contains("effective_external_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionSuspended =
            BetaGoogleManagedCmekRegionSuspended.of("effective_external_key_id")

        val roundtrippedBetaGoogleManagedCmekRegionSuspended =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionSuspended),
                jacksonTypeRef<BetaGoogleManagedCmekRegionSuspended>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionSuspended)
            .isEqualTo(betaGoogleManagedCmekRegionSuspended)
    }
}
