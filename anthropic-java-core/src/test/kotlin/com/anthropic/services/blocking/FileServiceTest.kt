// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
internal class FileServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileService = client.files()

        val page = fileService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileService = client.files()

        val deletedFile = fileService.delete("file_id")

        deletedFile.validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileService = client.files()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val response = fileService.download("file_id")

        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun retrieveMetadata() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileService = client.files()

        val fileMetadata = fileService.retrieveMetadata("file_id")

        fileMetadata.validate()
    }

    @Test
    fun upload() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val fileService = client.files()

        val fileMetadata =
            fileService.upload(
                FileUploadParams.builder()
                    .file("Example data".byteInputStream())
                    .expiresInSeconds(3600L)
                    .build()
            )

        fileMetadata.validate()
    }
}
