package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.externalstorage.BetaAwsExternalStorageConfigParam
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageActivateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageCreateParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageDeleteParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageRetrieveParams
import com.anthropic.models.beta.organization.externalstorage.ExternalStorageUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ExternalStorageServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val externalStorageService =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorage =
            externalStorageService.create(
                ExternalStorageCreateParams.builder()
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .providerConfig(
                        BetaAwsExternalStorageConfigParam.builder()
                            .accountId("111122223333")
                            .bucketName("acme-safety-retention")
                            .region("us-east-2")
                            .build()
                    )
                    .displayName("x")
                    .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                    .build()
            )

        betaExternalStorage.validate()
    }

    @Test
    fun retrieve() {
        val externalStorageService =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorage =
            externalStorageService.retrieve(
                ExternalStorageRetrieveParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaExternalStorage.validate()
    }

    @Test
    fun update() {
        val externalStorageService =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorage =
            externalStorageService.update(
                ExternalStorageUpdateParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .displayName("x")
                    .build()
            )

        betaExternalStorage.validate()
    }

    @Test
    fun list() {
        val externalStorageService =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = externalStorageService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val externalStorageService =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaDeletedExternalStorage =
            externalStorageService.delete(
                ExternalStorageDeleteParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaDeletedExternalStorage.validate()
    }

    @Test
    fun activate() {
        val externalStorageService =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorage =
            externalStorageService.activate(
                ExternalStorageActivateParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaExternalStorage.validate()
    }
}
