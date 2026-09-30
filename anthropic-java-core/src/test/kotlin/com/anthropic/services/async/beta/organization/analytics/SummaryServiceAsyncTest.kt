package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.analytics.summaries.SummaryListParams
import java.time.LocalDate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class SummaryServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val summaryServiceAsync = client.beta().organization().analytics().summaries()

        val pageFuture =
            summaryServiceAsync.list(
                SummaryListParams.builder().startingDate(LocalDate.parse("2019-12-27")).build()
            )

        val page = pageFuture.get()
        page.response().validate()
    }
}
