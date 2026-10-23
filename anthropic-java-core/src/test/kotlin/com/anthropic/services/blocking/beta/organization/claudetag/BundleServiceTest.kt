package com.anthropic.services.blocking.beta.organization.claudetag

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class BundleServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val bundleService =
            client.beta().organization().claudeTag().bundles().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundle =
            bundleService.retrieve(
                BundleRetrieveParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaClaudeTagBundle.validate()
    }

    @Test
    fun list() {
        val bundleService =
            client.beta().organization().claudeTag().bundles().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = bundleService.list()

        page.response().validate()
    }
}
