package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AwsExternalKeyConfigTest {

    @Test
    fun create() {
        val awsExternalKeyConfig =
            AwsExternalKeyConfig.builder()
                .kmsArn(
                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                )
                .region("us-east-1")
                .build()

        assertThat(awsExternalKeyConfig.kmsArn())
            .isEqualTo(
                "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
            )
        assertThat(awsExternalKeyConfig.region()).contains("us-east-1")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val awsExternalKeyConfig =
            AwsExternalKeyConfig.builder()
                .kmsArn(
                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                )
                .region("us-east-1")
                .build()

        val roundtrippedAwsExternalKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(awsExternalKeyConfig),
                jacksonTypeRef<AwsExternalKeyConfig>(),
            )

        assertThat(roundtrippedAwsExternalKeyConfig).isEqualTo(awsExternalKeyConfig)
    }
}
