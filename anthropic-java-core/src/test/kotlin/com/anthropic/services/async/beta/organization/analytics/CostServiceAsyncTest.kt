package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.analytics.cost.CostListParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CostServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val costServiceAsync =
            client.beta().organization().analytics().cost().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture =
            costServiceAsync.list(
                CostListParams.builder()
                    .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                    .build()
            )

        val page = pageFuture.get()
        page.response().validate()
    }
}
