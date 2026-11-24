package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedProjectCmekDisabledTest {

    @Test
    fun create() {
        val betaGoogleManagedProjectCmekDisabled =
            BetaGoogleManagedProjectCmekDisabled.builder()
                .addRetiredKey(
                    BetaGoogleManagedProjectRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .region("us")
                        .build()
                )
                .build()

        assertThat(betaGoogleManagedProjectCmekDisabled.retiredKeys())
            .containsExactly(
                BetaGoogleManagedProjectRetiredKey.builder()
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                    .region("us")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedProjectCmekDisabled =
            BetaGoogleManagedProjectCmekDisabled.builder()
                .addRetiredKey(
                    BetaGoogleManagedProjectRetiredKey.builder()
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .region("us")
                        .build()
                )
                .build()

        val roundtrippedBetaGoogleManagedProjectCmekDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedProjectCmekDisabled),
                jacksonTypeRef<BetaGoogleManagedProjectCmekDisabled>(),
            )

        assertThat(roundtrippedBetaGoogleManagedProjectCmekDisabled)
            .isEqualTo(betaGoogleManagedProjectCmekDisabled)
    }
}
