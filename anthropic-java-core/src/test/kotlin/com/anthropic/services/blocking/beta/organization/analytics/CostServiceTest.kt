package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.analytics.cost.CostListParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CostServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val costService = client.beta().organization().analytics().cost()

        val page =
            costService.list(
                CostListParams.builder()
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        page.response().validate()
    }
}
