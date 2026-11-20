package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGcpExternalStorageConfigTest {

    @Test
    fun create() {
        val betaGcpExternalStorageConfig =
            BetaGcpExternalStorageConfig.builder()
                .bucketName("acme-safety-retention")
                .projectNumber("123456789012")
                .region("us-east5")
                .build()

        assertThat(betaGcpExternalStorageConfig.bucketName()).isEqualTo("acme-safety-retention")
        assertThat(betaGcpExternalStorageConfig.projectNumber()).isEqualTo("123456789012")
        assertThat(betaGcpExternalStorageConfig.region()).isEqualTo("us-east5")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGcpExternalStorageConfig =
            BetaGcpExternalStorageConfig.builder()
                .bucketName("acme-safety-retention")
                .projectNumber("123456789012")
                .region("us-east5")
                .build()

        val roundtrippedBetaGcpExternalStorageConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGcpExternalStorageConfig),
                jacksonTypeRef<BetaGcpExternalStorageConfig>(),
            )

        assertThat(roundtrippedBetaGcpExternalStorageConfig).isEqualTo(betaGcpExternalStorageConfig)
    }
}
