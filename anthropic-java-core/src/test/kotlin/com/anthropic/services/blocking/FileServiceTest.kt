package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
internal class FileServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val fileService = client.files()

        val page = fileService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val fileService = client.files()

        val deletedFile =
            fileService.delete(
                FileDeleteParams.builder()
                    .fileId("file_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        deletedFile.validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val fileService = client.files().withOptions { it.baseUrl(wmRuntimeInfo.httpBaseUrl) }
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val response =
            fileService.download(
                FileDownloadParams.builder()
                    .fileId("file_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        assertThat(response.body()).hasContent("abc")
    }

    @Test
    fun retrieveMetadata() {
        val fileService = client.files()

        val fileMetadata =
            fileService.retrieveMetadata(
                FileRetrieveMetadataParams.builder()
                    .fileId("file_id")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        fileMetadata.validate()
    }

    @Test
    fun upload() {
        val fileService = client.files()

        val fileMetadata =
            fileService.upload(
                FileUploadParams.builder()
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .file("Example data".byteInputStream())
                    .expiresInSeconds(3600L)
                    .build()
            )

        fileMetadata.validate()
    }
}
