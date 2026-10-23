package com.anthropic.services.async.beta.skills

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.MultipartField
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.skills.versions.VersionCreateParams
import com.anthropic.models.beta.skills.versions.VersionDeleteParams
import com.anthropic.models.beta.skills.versions.VersionDownloadParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.io.InputStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.ResourceLock

@ExtendWith(TestServerExtension::class)
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class VersionServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val versionServiceAsync =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val betaSkillVersionFuture =
            versionServiceAsync.create(
                VersionCreateParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of<InputStream>("Example data".byteInputStream()))
                    .build()
            )

        val betaSkillVersion = betaSkillVersionFuture.get()
        betaSkillVersion.validate()
    }

    @Test
    fun retrieve() {
        val versionServiceAsync =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val betaSkillVersionFuture =
            versionServiceAsync.retrieve(
                VersionRetrieveParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaSkillVersion = betaSkillVersionFuture.get()
        betaSkillVersion.validate()
    }

    @Test
    fun list() {
        val versionServiceAsync =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = versionServiceAsync.list("skill_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val versionServiceAsync =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedSkillVersionFuture =
            versionServiceAsync.delete(
                VersionDeleteParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaDeletedSkillVersion = betaDeletedSkillVersionFuture.get()
        betaDeletedSkillVersion.validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val versionServiceAsync =
            client
                .beta()
                .skills()
                .versions()
                .withOptions { it.baseUrl(wmRuntimeInfo.httpBaseUrl) }
                .withOptions { it.putQueryParam("beta", "true") }
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val responseFuture =
            versionServiceAsync.download(
                VersionDownloadParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val response = responseFuture.get()
        assertThat(response.body()).hasContent("abc")
    }
}
