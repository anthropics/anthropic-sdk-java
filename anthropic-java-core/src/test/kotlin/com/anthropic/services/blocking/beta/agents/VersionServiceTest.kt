// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.agents

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class VersionServiceTest {

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val versionService = client.beta().agents().versions()

        val page = versionService.list("agent_011CZkYpogX7uDKUyvBTophP")

        page.response().validate()
    }
}
