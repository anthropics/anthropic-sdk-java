package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekRegionActiveTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekRegionActive =
            BetaGoogleManagedCmekRegionActive.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaGoogleManagedCmekRegionActive.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedCmekRegionActive.error()).contains("error")
        assertThat(betaGoogleManagedCmekRegionActive.lastCheckedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaGoogleManagedCmekRegionActive.validatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekRegionActive =
            BetaGoogleManagedCmekRegionActive.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaGoogleManagedCmekRegionActive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekRegionActive),
                jacksonTypeRef<BetaGoogleManagedCmekRegionActive>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekRegionActive)
            .isEqualTo(betaGoogleManagedCmekRegionActive)
    }
}
