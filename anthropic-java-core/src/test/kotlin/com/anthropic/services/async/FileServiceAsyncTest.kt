// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.files.FileDeleteParams
import com.anthropic.models.files.FileDownloadParams
import com.anthropic.models.files.FileRetrieveMetadataParams
import com.anthropic.models.files.FileUploadParams
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

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class FileServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileServiceAsync = client.files()

        val pageFuture = fileServiceAsync.list()

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
        val fileServiceAsync = client.files()

        val deletedFileFuture =
            fileServiceAsync.delete(
                FileDeleteParams.builder()
                    .fileId("file_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val deletedFile = deletedFileFuture.get()
        deletedFile.validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileServiceAsync = client.files()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val responseFuture =
            fileServiceAsync.download(
                FileDownloadParams.builder()
                    .fileId("file_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val response = responseFuture.get()
        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun retrieveMetadata() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileServiceAsync = client.files()

        val fileMetadataFuture =
            fileServiceAsync.retrieveMetadata(
                FileRetrieveMetadataParams.builder()
                    .fileId("file_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val fileMetadata = fileMetadataFuture.get()
        fileMetadata.validate()
    }

    @Test
    fun upload() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileServiceAsync = client.files()

        val fileMetadataFuture =
            fileServiceAsync.upload(
                FileUploadParams.builder()
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .file("Example data".byteInputStream())
                    .expiresInSeconds(3600L)
                    .build()
            )

        val fileMetadata = fileMetadataFuture.get()
        fileMetadata.validate()
    }
}
