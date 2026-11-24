package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectRetiredKeyTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectRetiredKey =
            BetaGoogleManagedProjectRetiredKey.builder()
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .region("us")
                .build()

        assertThat(betaGoogleManagedProjectRetiredKey.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaGoogleManagedProjectRetiredKey.externalKeyId())
            .isEqualTo("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaGoogleManagedProjectRetiredKey.region()).isEqualTo("us")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectRetiredKey =
            BetaGoogleManagedProjectRetiredKey.builder()
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .region("us")
                .build()

        val roundtrippedBetaGoogleManagedProjectRetiredKey =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectRetiredKey),
                jacksonTypeRef<BetaGoogleManagedProjectRetiredKey>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectRetiredKey)
            .isEqualTo(betaGoogleManagedProjectRetiredKey)
    }
}
