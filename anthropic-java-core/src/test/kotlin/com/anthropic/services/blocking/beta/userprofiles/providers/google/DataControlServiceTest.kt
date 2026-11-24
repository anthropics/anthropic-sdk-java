package com.anthropic.services.blocking.beta.userprofiles.providers.google

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.BetaGoogleManagedCmekEnabledParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.BetaGoogleManagedStorageDisabledParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlUpdateParams
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
            client.beta().userProfiles().providers().google().dataControls().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaGoogleManagedDataControls =
            dataControlService.retrieve(
                DataControlRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaGoogleManagedDataControls.validate()
    }

    @Test
    fun update() {
        val dataControlService =
            client.beta().userProfiles().providers().google().dataControls().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaGoogleManagedDataControls =
            dataControlService.update(
                DataControlUpdateParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .cmek(
                        BetaGoogleManagedCmekEnabledParams.builder()
                            .externalKeyIds(
                                BetaGoogleManagedCmekEnabledParams.ExternalKeyIds.builder()
                                    .putAdditionalProperty(
                                        "us",
                                        JsonValue.from("ekey_01SDCCSbTxrXDpWc1phhtcfK"),
                                    )
                                    .build()
                            )
                            .build()
                    )
                    .storage(BetaGoogleManagedStorageDisabledParams.builder().build())
                    .build()
            )

        betaGoogleManagedDataControls.validate()
    }
}
