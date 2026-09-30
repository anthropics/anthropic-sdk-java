package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.apikeys.ApiKeyUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ApiKeyServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val apiKeyService = client.organization().apiKeys()

        val apiKey = apiKeyService.retrieve("api_key_id")

        apiKey.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val apiKeyService = client.organization().apiKeys()

        val apiKey =
            apiKeyService.update(
                ApiKeyUpdateParams.builder()
                    .apiKeyId("api_key_id")
                    .name("x")
                    .status(ApiKeyUpdateParams.Status.ACTIVE)
                    .build()
            )

        apiKey.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val apiKeyService = client.organization().apiKeys()

        val page = apiKeyService.list()

        page.response().validate()
    }
}
