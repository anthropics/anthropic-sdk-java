package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyListPageResponseTest {

    @Test
    fun create() {
        val externalKeyListPageResponse =
            ExternalKeyListPageResponse.builder()
                .addData(
                    ExternalKey.builder()
                        .id("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .attachment(ExternalKeyAttachedAttachment.builder().build())
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .displayName("prod-us-key")
                        .geo("us")
                        .providerConfig(
                            AwsExternalKeyConfig.builder()
                                .kmsArn(
                                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                                )
                                .region("us-east-1")
                                .build()
                        )
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(externalKeyListPageResponse.data())
            .containsExactly(
                ExternalKey.builder()
                    .id("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                    .attachment(ExternalKeyAttachedAttachment.builder().build())
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .displayName("prod-us-key")
                    .geo("us")
                    .providerConfig(
                        AwsExternalKeyConfig.builder()
                            .kmsArn(
                                "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                            )
                            .region("us-east-1")
                            .build()
                    )
                    .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .build()
            )
        assertThat(externalKeyListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyListPageResponse =
            ExternalKeyListPageResponse.builder()
                .addData(
                    ExternalKey.builder()
                        .id("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                        .attachment(ExternalKeyAttachedAttachment.builder().build())
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .displayName("prod-us-key")
                        .geo("us")
                        .providerConfig(
                            AwsExternalKeyConfig.builder()
                                .kmsArn(
                                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                                )
                                .region("us-east-1")
                                .build()
                        )
                        .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedExternalKeyListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyListPageResponse),
                jacksonTypeRef<ExternalKeyListPageResponse>(),
            )

        assertThat(roundtrippedExternalKeyListPageResponse).isEqualTo(externalKeyListPageResponse)
    }
}
