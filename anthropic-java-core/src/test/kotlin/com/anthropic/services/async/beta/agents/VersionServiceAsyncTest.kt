// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.agents

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VersionServiceAsyncTest {

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionServiceAsync = client.beta().agents().versions()

        val pageFuture = versionServiceAsync.list("agent_011CZkYpogX7uDKUyvBTophP")

        val page = pageFuture.get()
        page.response().validate()
    }
}
