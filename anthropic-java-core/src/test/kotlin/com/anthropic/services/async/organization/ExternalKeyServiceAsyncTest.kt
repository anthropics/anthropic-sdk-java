package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.externalkeys.AwsExternalKeyConfig
import com.anthropic.models.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.organization.externalkeys.ExternalKeyUpdateParams
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
        val externalKeyServiceAsync = client.organization().externalKeys()

        val externalKeyFuture =
            externalKeyServiceAsync.create(
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
            )

        val externalKey = externalKeyFuture.get()
        externalKey.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.organization().externalKeys()

        val externalKeyFuture = externalKeyServiceAsync.retrieve("external_key_id")

        val externalKey = externalKeyFuture.get()
        externalKey.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.organization().externalKeys()

        val externalKeyFuture =
            externalKeyServiceAsync.update(
                ExternalKeyUpdateParams.builder()
                    .externalKeyId("external_key_id")
                    .displayName("x")
                    .geo(ExternalKeyUpdateParams.Geo.US)
                    .providerConfig(
                        AwsExternalKeyConfig.builder()
                            .kmsArn(
                                "arn:aws:kms:us-east-1:111122223333:key/abcd1234-5678-90ab-cdef-000011112222"
                            )
                            .region("us-east-1")
                            .build()
                    )
                    .build()
            )

        val externalKey = externalKeyFuture.get()
        externalKey.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyServiceAsync = client.organization().externalKeys()

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
        val externalKeyServiceAsync = client.organization().externalKeys()

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
        val externalKeyServiceAsync = client.organization().externalKeys()

        val responseFuture = externalKeyServiceAsync.validate("external_key_id")

        val response = responseFuture.get()
        response.validate()
    }
}
