package com.anthropic.services.blocking.beta.organization.providers.google.projects

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectCmekDisabledParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.BetaGoogleManagedProjectStorageDisabledParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.organization.providers.google.projects.datacontrols.DataControlUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DataControlServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val dataControlService =
            client
                .beta()
                .organization()
                .providers()
                .google()
                .projects()
                .dataControls()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaGoogleManagedProjectDataControls =
            dataControlService.retrieve(
                DataControlRetrieveParams.builder()
                    .projectNumber("123456789012")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .build()
            )

        betaGoogleManagedProjectDataControls.validate()
    }

    @Test
    fun update() {
        val dataControlService =
            client
                .beta()
                .organization()
                .providers()
                .google()
                .projects()
                .dataControls()
                .withOptions { it.putQueryParam("beta", "true") }

        val betaGoogleManagedProjectDataControls =
            dataControlService.update(
                DataControlUpdateParams.builder()
                    .projectNumber("123456789012")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .cmek(BetaGoogleManagedProjectCmekDisabledParams.builder().build())
                    .storage(BetaGoogleManagedProjectStorageDisabledParams.builder().build())
                    .build()
            )

        betaGoogleManagedProjectDataControls.validate()
    }
}
