package com.anthropic.services.blocking.beta.organization.spendlimits

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EffectiveServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val effectiveService =
            client.beta().organization().spendLimits().effective().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = effectiveService.list()

        page.response().validate()
    }
}
