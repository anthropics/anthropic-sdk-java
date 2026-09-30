package com.anthropic.services.blocking.beta.organization.analytics.apps.chat

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ProjectServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val projectService = client.beta().organization().analytics().apps().chat().projects()

        val page = projectService.list()

        page.response().validate()
    }
}
