package com.anthropic.services.blocking.beta.skills

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
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
internal class VersionServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val versionService =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val betaSkillVersion =
            versionService.create(
                VersionCreateParams.builder()
                    .skillId("skill_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addFile(MultipartField.of<InputStream>("Example data".byteInputStream()))
                    .build()
            )

        betaSkillVersion.validate()
    }

    @Test
    fun retrieve() {
        val versionService =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val betaSkillVersion =
            versionService.retrieve(
                VersionRetrieveParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaSkillVersion.validate()
    }

    @Test
    fun list() {
        val versionService =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val page = versionService.list("skill_id")

        page.response().validate()
    }

    @Test
    fun delete() {
        val versionService =
            client.beta().skills().versions().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedSkillVersion =
            versionService.delete(
                VersionDeleteParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaDeletedSkillVersion.validate()
    }

    @Test
    fun download(wmRuntimeInfo: WireMockRuntimeInfo) {
        val versionService =
            client
                .beta()
                .skills()
                .versions()
                .withOptions { it.baseUrl(wmRuntimeInfo.httpBaseUrl) }
                .withOptions { it.putQueryParam("beta", "true") }
        stubFor(get(anyUrl()).willReturn(ok().withBody("abc")))

        val response =
            versionService.download(
                VersionDownloadParams.builder()
                    .skillId("skill_id")
                    .version("version")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        assertThat(response.body()).hasContent("abc")
    }
}
