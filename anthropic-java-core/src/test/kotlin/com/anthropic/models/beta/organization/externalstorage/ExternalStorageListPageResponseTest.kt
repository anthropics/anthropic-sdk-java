package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalStorageListPageResponseTest {

    @Test
    fun create() {
        val externalStorageListPageResponse =
            ExternalStorageListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        assertThat(externalStorageListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(externalStorageListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalStorageListPageResponse =
            ExternalStorageListPageResponse.builder()
                .addData(
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
                )
                .nextPage("next_page")
                .build()

        val roundtrippedExternalStorageListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalStorageListPageResponse),
                jacksonTypeRef<ExternalStorageListPageResponse>(),
            )

        assertThat(roundtrippedExternalStorageListPageResponse)
            .isEqualTo(externalStorageListPageResponse)
    }
}
