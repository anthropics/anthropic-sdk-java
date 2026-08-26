// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.externalkeys.BetaAwsExternalKeyConfig
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ExternalKeyServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.beta().organization().externalKeys()

        val betaExternalKeyFuture =
            externalKeyServiceAsync.create(
                ExternalKeyCreateParams.builder()
                    .providerConfig(
                        BetaAwsExternalKeyConfig.builder()
                            .kmsArn(
                                "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                            )
                            .region("us-east-1")
                            .roleArn("arn:aws:iam::111122223333:role/anthropic-cmek")
                            .build()
                    )
                    .displayName("x")
                    .geo(ExternalKeyCreateParams.Geo.US)
                    .build()
            )

        val betaExternalKey = betaExternalKeyFuture.get()
        betaExternalKey.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.beta().organization().externalKeys()

        val betaExternalKeyFuture = externalKeyServiceAsync.retrieve("external_key_id")

        val betaExternalKey = betaExternalKeyFuture.get()
        betaExternalKey.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.beta().organization().externalKeys()

        val betaExternalKeyFuture =
            externalKeyServiceAsync.update(
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
            )

        val betaExternalKey = betaExternalKeyFuture.get()
        betaExternalKey.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.beta().organization().externalKeys()

        val pageFuture = externalKeyServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.beta().organization().externalKeys()

        val externalKeyFuture = externalKeyServiceAsync.delete("external_key_id")

        val externalKey = externalKeyFuture.get()
        externalKey.validate()
    }

    @Test
    fun validate() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.beta().organization().externalKeys()

        val responseFuture = externalKeyServiceAsync.validate("external_key_id")

        val response = responseFuture.get()
        response.validate()
    }
}
