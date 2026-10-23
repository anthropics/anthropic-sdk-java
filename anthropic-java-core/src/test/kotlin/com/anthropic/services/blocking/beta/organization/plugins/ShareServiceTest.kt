package com.anthropic.services.blocking.beta.organization.plugins

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ShareServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val shareService =
            client.beta().organization().plugins().shares().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = shareService.list("plugin_id")

        page.response().validate()
    }
}
