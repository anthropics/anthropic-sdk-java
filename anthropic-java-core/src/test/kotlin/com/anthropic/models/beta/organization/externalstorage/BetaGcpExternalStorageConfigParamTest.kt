package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaGcpExternalStorageConfigParamTest {

    @Test
    fun create() {
        val betaGcpExternalStorageConfigParam =
            BetaGcpExternalStorageConfigParam.builder()
                .bucketName("acme-safety-retention")
                .projectNumber("123456789012")
                .region("us-east5")
                .build()

        assertThat(betaGcpExternalStorageConfigParam.bucketName())
            .isEqualTo("acme-safety-retention")
        assertThat(betaGcpExternalStorageConfigParam.projectNumber()).isEqualTo("123456789012")
        assertThat(betaGcpExternalStorageConfigParam.region()).isEqualTo("us-east5")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaGcpExternalStorageConfigParam =
            BetaGcpExternalStorageConfigParam.builder()
                .bucketName("acme-safety-retention")
                .projectNumber("123456789012")
                .region("us-east5")
                .build()

        val roundtrippedBetaGcpExternalStorageConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaGcpExternalStorageConfigParam),
                jacksonTypeRef<BetaGcpExternalStorageConfigParam>(),
            )

        assertThat(roundtrippedBetaGcpExternalStorageConfigParam)
            .isEqualTo(betaGcpExternalStorageConfigParam)
    }
}
