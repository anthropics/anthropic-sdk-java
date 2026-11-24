package com.anthropic.services.async.beta.organization.providers.google.projects

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectCmekDisabledParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectStorageDisabledParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DataControlServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val dataControlServiceAsync =
            client
                .beta()
                .organization()
                .providers()
                .google()
                .projects()
                .dataControls()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaGoogleManagedProjectDataControlsFuture =
            dataControlServiceAsync.retrieve(
                DataControlRetrieveParams.builder()
                    .projectNumber("123456789012")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        val betaGoogleManagedProjectDataControls = betaGoogleManagedProjectDataControlsFuture.get()
        betaGoogleManagedProjectDataControls.validate()
    }

    @Test
    fun update() {
        val dataControlServiceAsync =
            client
                .beta()
                .organization()
                .providers()
                .google()
                .projects()
                .dataControls()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaGoogleManagedProjectDataControlsFuture =
            dataControlServiceAsync.update(
                DataControlUpdateParams.builder()
                    .projectNumber("123456789012")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .cmek(BetaGoogleManagedProjectCmekDisabledParams.builder().build())
                    .storage(BetaGoogleManagedProjectStorageDisabledParams.builder().build())
                    .build()
            )

        val betaGoogleManagedProjectDataControls = betaGoogleManagedProjectDataControlsFuture.get()
        betaGoogleManagedProjectDataControls.validate()
    }
}
