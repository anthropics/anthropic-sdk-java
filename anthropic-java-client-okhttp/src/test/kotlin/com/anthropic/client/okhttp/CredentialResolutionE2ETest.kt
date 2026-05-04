package com.anthropic.client.okhttp

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class CredentialResolutionE2ETest {

    private lateinit var baseUrl: String

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
    }

    @Test
    fun zeroConfigWithEnvApiKey() {
        stubFor(
            post(urlPathEqualTo("/v1/messages")).willReturn(ok().withBody("""{"result": "ok"}"""))
        )

        val client = AnthropicOkHttpClient.builder().baseUrl(baseUrl).apiKey("test-api-key").build()

        assertThat(client).isNotNull
    }

    @Test
    fun builderWithExplicitApiKey() {
        val client = AnthropicOkHttpClient.builder().apiKey("explicit-api-key").build()
        assertThat(client).isNotNull()
    }
}
