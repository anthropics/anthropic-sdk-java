package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAwsExternalStorageConfigTest {

    @Test
    fun create() {
        val betaAwsExternalStorageConfig =
            BetaAwsExternalStorageConfig.builder()
                .accountId("111122223333")
                .bucketName("acme-safety-retention")
                .region("us-east-2")
                .build()

        assertThat(betaAwsExternalStorageConfig.accountId()).isEqualTo("111122223333")
        assertThat(betaAwsExternalStorageConfig.bucketName()).isEqualTo("acme-safety-retention")
        assertThat(betaAwsExternalStorageConfig.region()).isEqualTo("us-east-2")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAwsExternalStorageConfig =
            BetaAwsExternalStorageConfig.builder()
                .accountId("111122223333")
                .bucketName("acme-safety-retention")
                .region("us-east-2")
                .build()

        val roundtrippedBetaAwsExternalStorageConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAwsExternalStorageConfig),
                jacksonTypeRef<BetaAwsExternalStorageConfig>(),
            )

        assertThat(roundtrippedBetaAwsExternalStorageConfig).isEqualTo(betaAwsExternalStorageConfig)
    }
}
