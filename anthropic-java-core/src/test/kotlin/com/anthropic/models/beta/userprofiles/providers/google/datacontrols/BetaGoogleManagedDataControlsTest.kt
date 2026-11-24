package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGoogleManagedDataControlsTest {

    @Test
    fun create() {
        val betaGoogleManagedDataControls =
            BetaGoogleManagedDataControls.builder()
                .bucketLabelValue("2f6d8c3a-91b4-4e57-8a0c-d5e1f7b3a924")
                .disabledCmek(
                    listOf(
                        BetaGoogleManagedRetiredKey.builder()
                            .expiresAt(OffsetDateTime.parse("2026-09-29T09:14:03Z"))
                            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                            .region("us")
                            .build()
                    )
                )
                .disabledStorage(listOf())
                .updatedAt(OffsetDateTime.parse("2026-08-30T09:14:03Z"))
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        assertThat(betaGoogleManagedDataControls.bucketLabelValue())
            .isEqualTo("2f6d8c3a-91b4-4e57-8a0c-d5e1f7b3a924")
        assertThat(betaGoogleManagedDataControls.cmek())
            .isEqualTo(
                BetaGoogleManagedCmekControl.ofDisabled(
                    BetaGoogleManagedCmekDisabled.builder()
                        .addRetiredKey(
                            BetaGoogleManagedRetiredKey.builder()
                                .expiresAt(OffsetDateTime.parse("2026-09-29T09:14:03Z"))
                                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                                .region("us")
                                .build()
                        )
                        .build()
                )
            )
        assertThat(betaGoogleManagedDataControls.storage())
            .isEqualTo(BetaGoogleManagedStorageControl.ofDisabled(listOf()))
        assertThat(betaGoogleManagedDataControls.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2026-08-30T09:14:03Z"))
        assertThat(betaGoogleManagedDataControls.userProfileId())
            .isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGoogleManagedDataControls =
            BetaGoogleManagedDataControls.builder()
                .bucketLabelValue("2f6d8c3a-91b4-4e57-8a0c-d5e1f7b3a924")
                .disabledCmek(
                    listOf(
                        BetaGoogleManagedRetiredKey.builder()
                            .expiresAt(OffsetDateTime.parse("2026-09-29T09:14:03Z"))
                            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                            .region("us")
                            .build()
                    )
                )
                .disabledStorage(listOf())
                .updatedAt(OffsetDateTime.parse("2026-08-30T09:14:03Z"))
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        val roundtrippedBetaGoogleManagedDataControls =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGoogleManagedDataControls),
                jacksonTypeRef<BetaGoogleManagedDataControls>(),
            )

        assertThat(roundtrippedBetaGoogleManagedDataControls)
            .isEqualTo(betaGoogleManagedDataControls)
    }
}
