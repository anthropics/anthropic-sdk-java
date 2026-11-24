package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekRegionActiveTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekRegionActive =
            BetaGoogleManagedProjectCmekRegionActive.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaGoogleManagedProjectCmekRegionActive.effectiveExternalKeyId())
            .contains("effective_external_key_id")
        assertThat(betaGoogleManagedProjectCmekRegionActive.error()).contains("error")
        assertThat(betaGoogleManagedProjectCmekRegionActive.lastCheckedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaGoogleManagedProjectCmekRegionActive.validatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekRegionActive =
            BetaGoogleManagedProjectCmekRegionActive.builder()
                .effectiveExternalKeyId("effective_external_key_id")
                .error("error")
                .lastCheckedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .validatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaGoogleManagedProjectCmekRegionActive =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekRegionActive),
                jacksonTypeRef<BetaGoogleManagedProjectCmekRegionActive>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekRegionActive)
            .isEqualTo(betaGoogleManagedProjectCmekRegionActive)
    }
}
