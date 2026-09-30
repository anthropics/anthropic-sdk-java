package com.anthropic.services.async.beta.organization.plugins

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.plugins.versions.VersionCreateParams
import com.anthropic.models.beta.organization.plugins.versions.VersionDownloadParams
import com.anthropic.models.beta.organization.plugins.versions.VersionRetrieveParams
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
internal class VersionServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().organization().plugins().versions()

        val betaPluginVersionFuture =
            versionServiceAsync.create(
                VersionCreateParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addFile("Example data".byteInputStream())
                    .releaseNotes("release_notes")
                    .build()
            )

        val betaPluginVersion = betaPluginVersionFuture.get()
        betaPluginVersion.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().organization().plugins().versions()

        val betaPluginVersionFuture =
            versionServiceAsync.retrieve(
                VersionRetrieveParams.builder()
                    .pluginId("plugin_id")
                    .version("version")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaPluginVersion = betaPluginVersionFuture.get()
        betaPluginVersion.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().organization().plugins().versions()

        val pageFuture = versionServiceAsync.list("plugin_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(wmRuntimeInfo.httpBaseUrl)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().organization().plugins().versions()
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val responseFuture =
            versionServiceAsync.download(
                VersionDownloadParams.builder()
                    .pluginId("plugin_id")
                    .version("version")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val response = responseFuture.get()
        assertThat(response.body()).hasContent("abc")
    }
}
