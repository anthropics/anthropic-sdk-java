package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.apikeys.ApiKeyUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ApiKeyServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val apiKeyServiceAsync =
            client.beta().organization().apiKeys().withOptions { it.putQueryParam("beta", "true") }

        val betaApiKeyFuture = apiKeyServiceAsync.retrieve("api_key_id")

        val betaApiKey = betaApiKeyFuture.get()
        betaApiKey.validate()
    }

    @Test
    fun update() {
        val apiKeyServiceAsync =
            client.beta().organization().apiKeys().withOptions { it.putQueryParam("beta", "true") }

        val betaApiKeyFuture =
            apiKeyServiceAsync.update(
                ApiKeyUpdateParams.builder()
                    .apiKeyId("api_key_id")
                    .name("x")
                    .status(ApiKeyUpdateParams.Status.ACTIVE)
                    .build()
            )

        val betaApiKey = betaApiKeyFuture.get()
        betaApiKey.validate()
    }

    @Test
    fun list() {
        val apiKeyServiceAsync =
            client.beta().organization().apiKeys().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = apiKeyServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
