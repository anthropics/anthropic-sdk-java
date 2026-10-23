package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class PluginServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val pluginServiceAsync =
            client.beta().organization().analytics().plugins().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = pluginServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
