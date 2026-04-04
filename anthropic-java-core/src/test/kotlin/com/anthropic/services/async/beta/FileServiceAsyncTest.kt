// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.files.FileDeleteParams
import com.anthropic.models.beta.files.FileDownloadParams
import com.anthropic.models.beta.files.FileRetrieveMetadataParams
import com.anthropic.models.beta.files.FileUploadParams
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceLock
import kotlinx.coroutines.runBlocking

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class FileServiceAsyncTest {

    @Test
    fun list() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val fileServiceAsync = client.beta().files()

            val page = fileServiceAsync.list()

            page.response().validate()
        }
    }

    @Test
    fun delete() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val fileServiceAsync = client.beta().files()

            val deletedFile =
                fileServiceAsync.delete(
                    FileDeleteParams.builder()
                        .fileId("file_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            deletedFile.validate()
        }
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(wmRuntimeInfo.httpBaseUrl)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val fileServiceAsync = client.beta().files()
            stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

            val response =
                fileServiceAsync.download(
                    FileDownloadParams.builder()
                        .fileId("file_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            assertThat(response.body().readUtf8()).isEqualTo("abc")
        }
    }

    @Test
    fun retrieveMetadata() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val fileServiceAsync = client.beta().files()

            val fileMetadata =
                fileServiceAsync.retrieveMetadata(
                    FileRetrieveMetadataParams.builder()
                        .fileId("file_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            fileMetadata.validate()
        }
    }

    @Test
    fun upload() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val fileServiceAsync = client.beta().files()

            val fileMetadata =
                fileServiceAsync.upload(
                    FileUploadParams.builder()
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .file("Example data".byteInputStream())
                        .build()
                )

            fileMetadata.validate()
        }
    }
}
