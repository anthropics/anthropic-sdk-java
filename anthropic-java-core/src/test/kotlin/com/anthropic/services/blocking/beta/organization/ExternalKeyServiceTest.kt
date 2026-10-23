package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.externalkeys.BetaAwsExternalKeyConfig
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyCreateParams
import com.anthropic.models.beta.organization.externalkeys.ExternalKeyUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ExternalKeyServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val externalKeyService =
            client.beta().organization().externalKeys().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalKey =
            externalKeyService.create(
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

        betaExternalKey.validate()
    }

    @Test
    fun retrieve() {
        val externalKeyService =
            client.beta().organization().externalKeys().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalKey = externalKeyService.retrieve("external_key_id")

        betaExternalKey.validate()
    }

    @Test
    fun update() {
        val externalKeyService =
            client.beta().organization().externalKeys().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalKey =
            externalKeyService.update(
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

        betaExternalKey.validate()
    }

    @Test
    fun list() {
        val externalKeyService =
            client.beta().organization().externalKeys().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = externalKeyService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val externalKeyService =
            client.beta().organization().externalKeys().withOptions {
                it.putQueryParam("beta", "true")
            }

        val externalKey = externalKeyService.delete("external_key_id")

        externalKey.validate()
    }

    @Test
    fun validate() {
        val externalKeyService =
            client.beta().organization().externalKeys().withOptions {
                it.putQueryParam("beta", "true")
            }

        val response = externalKeyService.validate("external_key_id")

        response.validate()
    }
}
