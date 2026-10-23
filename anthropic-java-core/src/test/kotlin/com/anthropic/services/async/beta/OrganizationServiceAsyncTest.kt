package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class OrganizationServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val organizationServiceAsync =
            client.beta().organization().withOptions { it.putQueryParam("beta", "true") }

        val betaOrganizationFuture = organizationServiceAsync.retrieve()

        val betaOrganization = betaOrganizationFuture.get()
        betaOrganization.validate()
    }
}
