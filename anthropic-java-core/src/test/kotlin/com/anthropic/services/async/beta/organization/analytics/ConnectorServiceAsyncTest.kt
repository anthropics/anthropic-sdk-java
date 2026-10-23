package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ConnectorServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val connectorServiceAsync =
            client.beta().organization().analytics().connectors().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = connectorServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
