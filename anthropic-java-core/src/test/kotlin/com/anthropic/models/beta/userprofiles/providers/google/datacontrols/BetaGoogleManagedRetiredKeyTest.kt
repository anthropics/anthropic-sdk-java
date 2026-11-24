package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedRetiredKeyTest {

    @Test
    fun create() {
        val betaGoogleManagedRetiredKey =
            BetaGoogleManagedRetiredKey.builder()
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .externalKeyId("external_key_id")
                .region("region")
                .build()

        assertThat(betaGoogleManagedRetiredKey.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaGoogleManagedRetiredKey.externalKeyId()).isEqualTo("external_key_id")
        assertThat(betaGoogleManagedRetiredKey.region()).isEqualTo("region")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedRetiredKey =
            BetaGoogleManagedRetiredKey.builder()
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .externalKeyId("external_key_id")
                .region("region")
                .build()

        val roundtrippedBetaGoogleManagedRetiredKey =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedRetiredKey),
                jacksonTypeRef<BetaGoogleManagedRetiredKey>(),
            )

        assertThat(roundtrippedBetaGoogleManagedRetiredKey).isEqualTo(betaGoogleManagedRetiredKey)
    }
}
