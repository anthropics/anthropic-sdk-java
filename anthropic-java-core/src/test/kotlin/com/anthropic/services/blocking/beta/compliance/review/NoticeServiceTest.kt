package com.anthropic.services.blocking.beta.compliance.review

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class NoticeServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val noticeService =
            client.beta().compliance().review().notices().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = noticeService.list()

        page.response().validate()
    }
}
