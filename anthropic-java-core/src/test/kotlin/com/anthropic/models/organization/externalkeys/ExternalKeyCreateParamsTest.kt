package com.anthropic.models.organization.externalkeys

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyCreateParamsTest {

    @Test
    fun create() {
        ExternalKeyCreateParams.builder()
            .providerConfig(
                AwsExternalKeyConfig.builder()
                    .kmsArn(
                        "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                    )
                    .region("us-east-1")
                    .build()
            )
            .displayName("x")
            .geo(ExternalKeyCreateParams.Geo.US)
            .build()
    }

    @Test
    fun body() {
        val params =
            ExternalKeyCreateParams.builder()
                .providerConfig(
                    AwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .build()
                )
                .displayName("x")
                .geo(ExternalKeyCreateParams.Geo.US)
                .build()

        val body = params._body()

        assertThat(body.providerConfig())
            .isEqualTo(
                ExternalKeyCreateParams.ProviderConfig.ofAws(
                    AwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .build()
                )
            )
        assertThat(body.displayName()).contains("x")
        assertThat(body.geo()).contains(ExternalKeyCreateParams.Geo.US)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ExternalKeyCreateParams.builder()
                .awsProviderConfig(
                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                )
                .build()

        val body = params._body()

        assertThat(body.providerConfig())
            .isEqualTo(
                ExternalKeyCreateParams.ProviderConfig.ofAws(
                    "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                )
            )
    }
}
