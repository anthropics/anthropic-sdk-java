package com.anthropic.services.async.organization.analytics.apps.chat

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ProjectServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val projectServiceAsync = client.organization().analytics().apps().chat().projects()

        val pageFuture = projectServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
