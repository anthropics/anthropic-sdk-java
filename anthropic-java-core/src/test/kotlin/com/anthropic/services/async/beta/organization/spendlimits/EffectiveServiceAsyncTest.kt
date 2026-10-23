package com.anthropic.services.async.beta.organization.spendlimits

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EffectiveServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val effectiveServiceAsync =
            client.beta().organization().spendLimits().effective().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = effectiveServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
