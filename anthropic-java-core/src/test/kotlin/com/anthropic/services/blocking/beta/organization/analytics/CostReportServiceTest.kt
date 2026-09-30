package com.anthropic.services.blocking.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.organization.analytics.costreport.CostReportListParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class CostReportServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val costReportService = client.beta().organization().analytics().costReport()

        val page =
            costReportService.list(
                CostReportListParams.builder()
                    .startingAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .build()
            )

        page.response().validate()
    }
}
