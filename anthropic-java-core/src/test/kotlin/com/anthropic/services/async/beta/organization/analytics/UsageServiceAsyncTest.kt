package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.analytics.usage.UsageListParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UsageServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val usageServiceAsync = client.beta().organization().analytics().usage()

        val pageFuture =
            usageServiceAsync.list(
                UsageListParams.builder()
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        val page = pageFuture.get()
        page.response().validate()
    }
}
