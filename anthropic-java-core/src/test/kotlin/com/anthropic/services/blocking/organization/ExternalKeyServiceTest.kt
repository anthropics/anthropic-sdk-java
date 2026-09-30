package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.externalkeys.AwsExternalKeyConfig
import com.anthropic.models.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.organization.externalkeys.ExternalKeyUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ExternalKeyServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyService = client.organization().externalKeys()

        val externalKey =
            externalKeyService.create(
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

        externalKey.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyService = client.organization().externalKeys()

        val externalKey = externalKeyService.retrieve("external_key_id")

        externalKey.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyService = client.organization().externalKeys()

        val externalKey =
            externalKeyService.update(
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

        externalKey.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyService = client.organization().externalKeys()

        val page = externalKeyService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyService = client.organization().externalKeys()

        val externalKey = externalKeyService.delete("external_key_id")

        externalKey.validate()
    }

    @Test
    fun validate() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val externalKeyService = client.organization().externalKeys()

        val response = externalKeyService.validate("external_key_id")

        response.validate()
    }
}
