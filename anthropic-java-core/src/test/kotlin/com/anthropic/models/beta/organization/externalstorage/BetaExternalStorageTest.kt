package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalStorageTest {

    @Test
    fun create() {
        val betaExternalStorage =
            BetaExternalStorage.builder()
                .id("estrg_01SDCCSbTxrXDpWc1phhtcfK")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("display_name")
                .providerConfig(
                    BetaAwsExternalStorageConfig.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
                .setupTemplateVersion("2")
                .status(BetaPendingConfigurationExternalStorageStatus.builder().build())
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                .build()

        assertThat(betaExternalStorage.id()).isEqualTo("estrg_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaExternalStorage.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaExternalStorage.displayName()).contains("display_name")
        assertThat(betaExternalStorage.providerConfig())
            .isEqualTo(
                BetaExternalStorage.ProviderConfig.ofAws(
                    BetaAwsExternalStorageConfig.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
            )
        assertThat(betaExternalStorage.setupTemplateVersion()).contains("2")
        assertThat(betaExternalStorage.status())
            .isEqualTo(
                BetaExternalStorage.Status.ofPendingConfiguration(
                    BetaPendingConfigurationExternalStorageStatus.builder().build()
                )
            )
        assertThat(betaExternalStorage.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaExternalStorage.userProfileId()).contains("uprof_01SDCCSbTxrXDpWc1phhtcfK")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalStorage =
            BetaExternalStorage.builder()
                .id("estrg_01SDCCSbTxrXDpWc1phhtcfK")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("display_name")
                .providerConfig(
                    BetaAwsExternalStorageConfig.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
                .setupTemplateVersion("2")
                .status(BetaPendingConfigurationExternalStorageStatus.builder().build())
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                .build()

        val roundtrippedBetaExternalStorage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalStorage),
                jacksonTypeRef<BetaExternalStorage>(),
            )

        assertThat(roundtrippedBetaExternalStorage).isEqualTo(betaExternalStorage)
    }
}
