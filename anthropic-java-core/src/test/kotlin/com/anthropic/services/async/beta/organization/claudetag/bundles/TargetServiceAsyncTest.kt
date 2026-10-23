package com.anthropic.services.async.beta.organization.claudetag.bundles

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetCreateParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetDeleteParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class TargetServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val targetServiceAsync =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundleTargetFuture =
            targetServiceAsync.create(
                TargetCreateParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .slackChannelTarget("xxxxxxxx")
                    .build()
            )

        val betaClaudeTagBundleTarget = betaClaudeTagBundleTargetFuture.get()
        betaClaudeTagBundleTarget.validate()
    }

    @Test
    fun retrieve() {
        val targetServiceAsync =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundleTargetFuture =
            targetServiceAsync.retrieve(
                TargetRetrieveParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaClaudeTagBundleTarget = betaClaudeTagBundleTargetFuture.get()
        betaClaudeTagBundleTarget.validate()
    }

    @Test
    fun list() {
        val targetServiceAsync =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = targetServiceAsync.list("capp_011CZkZjLP1cWxj5EtMmdgbq")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val targetServiceAsync =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagDeletedBundleTargetFuture =
            targetServiceAsync.delete(
                TargetDeleteParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaClaudeTagDeletedBundleTarget = betaClaudeTagDeletedBundleTargetFuture.get()
        betaClaudeTagDeletedBundleTarget.validate()
    }
}
