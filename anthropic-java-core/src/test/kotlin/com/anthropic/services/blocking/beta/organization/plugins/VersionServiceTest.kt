package com.anthropic.services.blocking.beta.organization.plugins

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
internal class VersionServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val versionService =
            client.beta().organization().plugins().versions().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaPluginVersion =
            versionService.create(
                VersionCreateParams.builder()
                    .pluginId("plugin_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addFile("Example data".byteInputStream())
                    .releaseNotes("release_notes")
                    .build()
            )

        betaPluginVersion.validate()
    }

    @Test
    fun retrieve() {
        val versionService =
            client.beta().organization().plugins().versions().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaPluginVersion =
            versionService.retrieve(
                VersionRetrieveParams.builder()
                    .pluginId("plugin_id")
                    .version("version")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaPluginVersion.validate()
    }

    @Test
    fun list() {
        val versionService =
            client.beta().organization().plugins().versions().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = versionService.list("plugin_id")

        page.response().validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val versionService =
            client
                .beta()
                .organization()
                .plugins()
                .versions()
                .withOptions { it.baseUrl(wmRuntimeInfo.httpBaseUrl) }
                .withOptions { it.putQueryParam("beta", "true") }
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val response =
            versionService.download(
                VersionDownloadParams.builder()
                    .pluginId("plugin_id")
                    .version("version")
                    .organizationId("organization_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        assertThat(response.body()).hasContent("abc")
    }
}
