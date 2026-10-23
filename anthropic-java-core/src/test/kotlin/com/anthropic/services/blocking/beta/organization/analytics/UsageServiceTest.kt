package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.analytics.usage.UsageListParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UsageServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val usageService =
            client.beta().organization().analytics().usage().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page =
            usageService.list(
                UsageListParams.builder()
                    .startingAt(OffsetDateTime.parse("2026-09-01T00:00:00Z"))
                    .build()
            )

        page.response().validate()
    }
}
