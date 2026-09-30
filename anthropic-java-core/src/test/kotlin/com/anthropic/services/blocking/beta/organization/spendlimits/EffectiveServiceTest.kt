package com.anthropic.services.blocking.beta.organization.spendlimits

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EffectiveServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val effectiveService = client.beta().organization().spendLimits().effective()

        val page = effectiveService.list()

        page.response().validate()
    }
}
