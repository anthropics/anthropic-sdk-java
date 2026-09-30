package com.anthropic.services.async.beta.organization.spendlimits

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EffectiveServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val effectiveServiceAsync = client.beta().organization().spendLimits().effective()

        val pageFuture = effectiveServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
