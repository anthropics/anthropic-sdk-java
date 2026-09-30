package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyTest {

    @Test
    fun create() {
        val externalKey =
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

        assertThat(externalKey.id()).isEqualTo("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(externalKey.attachment())
            .isEqualTo(
                ExternalKey.Attachment.ofAttached(ExternalKeyAttachedAttachment.builder().build())
            )
        assertThat(externalKey.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(externalKey.displayName()).contains("prod-us-key")
        assertThat(externalKey.geo()).isEqualTo("us")
        assertThat(externalKey.providerConfig())
            .isEqualTo(
                ExternalKey.ProviderConfig.ofAws(
                    AwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .build()
                )
            )
        assertThat(externalKey.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKey =
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

        val roundtrippedExternalKey =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKey),
                jacksonTypeRef<ExternalKey>(),
            )

        assertThat(roundtrippedExternalKey).isEqualTo(externalKey)
    }
}
