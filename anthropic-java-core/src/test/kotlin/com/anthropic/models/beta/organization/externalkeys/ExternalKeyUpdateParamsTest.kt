// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyUpdateParamsTest {

    @Test
    fun create() {
        ExternalKeyUpdateParams.builder()
            .externalKeyId("external_key_id")
            .displayName("x")
            .geo(ExternalKeyUpdateParams.Geo.US)
            .providerConfig(
                BetaAwsExternalKeyConfig.builder()
                    .kmsArn(
                        "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                    )
                    .region("us-east-1")
                    .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params = ExternalKeyUpdateParams.builder().externalKeyId("external_key_id").build()

        assertThat(params._pathParam(0)).isEqualTo("external_key_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            ExternalKeyUpdateParams.builder()
                .externalKeyId("external_key_id")
                .displayName("x")
                .geo(ExternalKeyUpdateParams.Geo.US)
                .providerConfig(
                    BetaAwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.displayName()).contains("x")
        assertThat(body.geo()).contains(ExternalKeyUpdateParams.Geo.US)
        assertThat(body.providerConfig())
            .contains(
                ExternalKeyUpdateParams.ProviderConfig.ofAws(
                    BetaAwsExternalKeyConfig.builder()
                        .kmsArn(
                            "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                        )
                        .region("us-east-1")
                        .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                        .build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ExternalKeyUpdateParams.builder().externalKeyId("external_key_id").build()

        val body = params._body()
    }
}
