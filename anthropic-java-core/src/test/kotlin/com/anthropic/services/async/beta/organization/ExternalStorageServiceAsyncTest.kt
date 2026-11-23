package com.anthropic.services.async.beta.organization

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class ExternalStorageServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val externalStorageServiceAsync =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorageFuture =
            externalStorageServiceAsync.create(
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

        val betaExternalStorage = betaExternalStorageFuture.get()
        betaExternalStorage.validate()
    }

    @Test
    fun retrieve() {
        val externalStorageServiceAsync =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorageFuture =
            externalStorageServiceAsync.retrieve(
                ExternalStorageRetrieveParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaExternalStorage = betaExternalStorageFuture.get()
        betaExternalStorage.validate()
    }

    @Test
    fun update() {
        val externalStorageServiceAsync =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorageFuture =
            externalStorageServiceAsync.update(
                ExternalStorageUpdateParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .displayName("x")
                    .build()
            )

        val betaExternalStorage = betaExternalStorageFuture.get()
        betaExternalStorage.validate()
    }

    @Test
    fun list() {
        val externalStorageServiceAsync =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = externalStorageServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val externalStorageServiceAsync =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaDeletedExternalStorageFuture =
            externalStorageServiceAsync.delete(
                ExternalStorageDeleteParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaDeletedExternalStorage = betaDeletedExternalStorageFuture.get()
        betaDeletedExternalStorage.validate()
    }

    @Test
    fun activate() {
        val externalStorageServiceAsync =
            client.beta().organization().externalStorage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaExternalStorageFuture =
            externalStorageServiceAsync.activate(
                ExternalStorageActivateParams.builder()
                    .externalStorageId("external_storage_id")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaExternalStorage = betaExternalStorageFuture.get()
        betaExternalStorage.validate()
    }
}
