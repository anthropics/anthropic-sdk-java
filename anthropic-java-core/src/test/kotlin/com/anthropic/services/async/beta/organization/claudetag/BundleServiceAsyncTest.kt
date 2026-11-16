package com.anthropic.services.async.beta.organization.claudetag

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class BundleServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val bundleServiceAsync =
            client.beta().organization().claudeTag().bundles().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundleFuture =
            bundleServiceAsync.retrieve(
                BundleRetrieveParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaClaudeTagBundle = betaClaudeTagBundleFuture.get()
        betaClaudeTagBundle.validate()
    }

    @Test
    fun update() {
        val bundleServiceAsync =
            client.beta().organization().claudeTag().bundles().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundleFuture =
            bundleServiceAsync.update(
                BundleUpdateParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .instructions("instructions")
                    .build()
            )

        val betaClaudeTagBundle = betaClaudeTagBundleFuture.get()
        betaClaudeTagBundle.validate()
    }

    @Test
    fun list() {
        val bundleServiceAsync =
            client.beta().organization().claudeTag().bundles().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = bundleServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
