package com.anthropic.services.async.beta.compliance.review

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class NoticeServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val noticeServiceAsync =
            client.beta().compliance().review().notices().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = noticeServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
