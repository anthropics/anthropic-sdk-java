package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAwsExternalStorageConfigParamTest {

    @Test
    fun create() {
        val betaAwsExternalStorageConfigParam =
            BetaAwsExternalStorageConfigParam.builder()
                .accountId("111122223333")
                .bucketName("acme-safety-retention")
                .region("us-east-2")
                .build()

        assertThat(betaAwsExternalStorageConfigParam.accountId()).isEqualTo("111122223333")
        assertThat(betaAwsExternalStorageConfigParam.bucketName())
            .isEqualTo("acme-safety-retention")
        assertThat(betaAwsExternalStorageConfigParam.region()).isEqualTo("us-east-2")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAwsExternalStorageConfigParam =
            BetaAwsExternalStorageConfigParam.builder()
                .accountId("111122223333")
                .bucketName("acme-safety-retention")
                .region("us-east-2")
                .build()

        val roundtrippedBetaAwsExternalStorageConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAwsExternalStorageConfigParam),
                jacksonTypeRef<BetaAwsExternalStorageConfigParam>(),
            )

        assertThat(roundtrippedBetaAwsExternalStorageConfigParam)
            .isEqualTo(betaAwsExternalStorageConfigParam)
    }
}
