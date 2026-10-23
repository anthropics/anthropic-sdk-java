package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val userService =
            client.beta().organization().analytics().users().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = userService.list()

        page.response().validate()
    }
}
