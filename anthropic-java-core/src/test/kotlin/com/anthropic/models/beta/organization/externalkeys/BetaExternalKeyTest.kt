// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaExternalKeyTest {

    @Test
    fun create() {
        val betaExternalKey =
            BetaExternalKey.builder()
                .id("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .attachment(BetaExternalKeyAttachedAttachment.builder().build())
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("prod-us-key")
                .geo("us")
                .providerConfig(
                    BetaAwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(betaExternalKey.id()).isEqualTo("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaExternalKey.attachment())
            .isEqualTo(
                BetaExternalKey.Attachment.ofAttached(
                    BetaExternalKeyAttachedAttachment.builder().build()
                )
            )
        assertThat(betaExternalKey.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaExternalKey.displayName()).contains("prod-us-key")
        assertThat(betaExternalKey.geo()).isEqualTo("us")
        assertThat(betaExternalKey.providerConfig())
            .isEqualTo(
                BetaExternalKey.ProviderConfig.ofAws(
                    BetaAwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                        .build()
                )
            )
        assertThat(betaExternalKey.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaExternalKey =
            BetaExternalKey.builder()
                .id("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .attachment(BetaExternalKeyAttachedAttachment.builder().build())
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("prod-us-key")
                .geo("us")
                .providerConfig(
                    BetaAwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                        .build()
                )
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedBetaExternalKey =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaExternalKey),
                jacksonTypeRef<BetaExternalKey>(),
            )

        assertThat(roundtrippedBetaExternalKey).isEqualTo(betaExternalKey)
    }
}
