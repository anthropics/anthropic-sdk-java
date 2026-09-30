package com.anthropic.services.async.beta.organization.analytics

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.analytics.artifacts.ArtifactListParams
import java.time.LocalDate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ArtifactServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val artifactServiceAsync = client.beta().organization().analytics().artifacts()

        val pageFuture =
            artifactServiceAsync.list(
                ArtifactListParams.builder().date(LocalDate.parse("2019-12-27")).build()
            )

        val page = pageFuture.get()
        page.response().validate()
    }
}
