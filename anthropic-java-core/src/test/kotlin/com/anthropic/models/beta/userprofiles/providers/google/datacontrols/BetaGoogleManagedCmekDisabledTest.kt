package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedCmekDisabledTest {

    @Test
    fun create() {
        val betaGoogleManagedCmekDisabled =
            BetaGoogleManagedCmekDisabled.builder()
                .addRetiredKey(
                    BetaGoogleManagedRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("external_key_id")
                        .region("region")
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedCmekDisabled.retiredKeys())
            .containsExactly(
                BetaGoogleManagedRetiredKey.builder()
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .externalKeyId("external_key_id")
                    .region("region")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedCmekDisabled =
            BetaGoogleManagedCmekDisabled.builder()
                .addRetiredKey(
                    BetaGoogleManagedRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("external_key_id")
                        .region("region")
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedCmekDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedCmekDisabled),
                jacksonTypeRef<BetaGoogleManagedCmekDisabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedCmekDisabled)
            .isEqualTo(betaGoogleManagedCmekDisabled)
    }
}
