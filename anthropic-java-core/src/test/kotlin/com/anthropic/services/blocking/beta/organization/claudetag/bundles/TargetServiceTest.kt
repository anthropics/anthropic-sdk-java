package com.anthropic.services.blocking.beta.organization.claudetag.bundles

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetCreateParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetDeleteParams
import com.anthropic.models.beta.organization.claudetag.bundles.targets.TargetRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class TargetServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val targetService =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundleTarget =
            targetService.create(
                TargetCreateParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .slackChannelTarget("xxxxxxxx")
                    .build()
            )

        betaClaudeTagBundleTarget.validate()
    }

    @Test
    fun retrieve() {
        val targetService =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagBundleTarget =
            targetService.retrieve(
                TargetRetrieveParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaClaudeTagBundleTarget.validate()
    }

    @Test
    fun list() {
        val targetService =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = targetService.list("capp_011CZkZjLP1cWxj5EtMmdgbq")

        page.response().validate()
    }

    @Test
    fun delete() {
        val targetService =
            client.beta().organization().claudeTag().bundles().targets().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaClaudeTagDeletedBundleTarget =
            targetService.delete(
                TargetDeleteParams.builder()
                    .bundleId("capp_011CZkZjLP1cWxj5EtMmdgbq")
                    .targetId("btgt_01SvpyRQK3nN6eH9uhRA7n1qHq8TzW5mKc2YbX7vNd4RfJ")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaClaudeTagDeletedBundleTarget.validate()
    }
}
