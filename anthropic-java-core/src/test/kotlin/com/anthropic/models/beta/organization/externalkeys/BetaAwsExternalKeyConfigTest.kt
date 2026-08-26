// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAwsExternalKeyConfigTest {

    @Test
    fun create() {
        val betaAwsExternalKeyConfig =
            BetaAwsExternalKeyConfig.builder()
                .kmsArn(
                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                )
                .region("us-east-1")
                .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                .build()

        assertThat(betaAwsExternalKeyConfig.kmsArn())
            .isEqualTo(
                "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
            )
        assertThat(betaAwsExternalKeyConfig.region()).contains("us-east-1")
        assertThat(betaAwsExternalKeyConfig.roleArn())
            .contains("arn:aws:iam::111122223333:role/anthropic-cmek")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAwsExternalKeyConfig =
            BetaAwsExternalKeyConfig.builder()
                .kmsArn(
                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                )
                .region("us-east-1")
                .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                .build()

        val roundtrippedBetaAwsExternalKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAwsExternalKeyConfig),
                jacksonTypeRef<BetaAwsExternalKeyConfig>(),
            )

        assertThat(roundtrippedBetaAwsExternalKeyConfig).isEqualTo(betaAwsExternalKeyConfig)
    }
}
