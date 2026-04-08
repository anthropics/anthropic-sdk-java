// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.resources.ResourceAddParams
import com.anthropic.models.beta.sessions.resources.ResourceDeleteParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveParams
import com.anthropic.models.beta.sessions.resources.ResourceUpdateParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ResourceServiceAsyncTest {

    @Disabled("prism can't find endpoint with beta only tag")
    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val resourceServiceAsync = client.beta().sessions().resources()

        val resourceFuture =
            resourceServiceAsync.retrieve(
                ResourceRetrieveParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val resource = resourceFuture.get()
        resource.validate()
    }

    @Disabled("prism can't find endpoint with beta only tag")
    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val resourceServiceAsync = client.beta().sessions().resources()

        val resourceFuture =
            resourceServiceAsync.update(
                ResourceUpdateParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .authorizationToken("ghp_exampletoken")
                    .build()
            )

        val resource = resourceFuture.get()
        resource.validate()
    }

    @Disabled("prism can't find endpoint with beta only tag")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val resourceServiceAsync = client.beta().sessions().resources()

        val pageFuture = resourceServiceAsync.list("sesn_011CZkZAtmR3yMPDzynEDxu7")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Disabled("prism can't find endpoint with beta only tag")
    @Test
    fun delete() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val resourceServiceAsync = client.beta().sessions().resources()

        val betaManagedAgentsDeleteSessionResourceFuture =
            resourceServiceAsync.delete(
                ResourceDeleteParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .resourceId("sesrsc_011CZkZBJq5dWxk9fVLNcPht")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaManagedAgentsDeleteSessionResource =
            betaManagedAgentsDeleteSessionResourceFuture.get()
        betaManagedAgentsDeleteSessionResource.validate()
    }

    @Disabled("prism can't find endpoint with beta only tag")
    @Test
    fun add() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val resourceServiceAsync = client.beta().sessions().resources()

        val betaManagedAgentsFileResourceFuture =
            resourceServiceAsync.add(
                ResourceAddParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .betaManagedAgentsFileResourceParams(
                        BetaManagedAgentsFileResourceParams.builder()
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                            .mountPath("/uploads/receipt.pdf")
                            .build()
                    )
                    .build()
            )

        val betaManagedAgentsFileResource = betaManagedAgentsFileResourceFuture.get()
        betaManagedAgentsFileResource.validate()
    }
}
