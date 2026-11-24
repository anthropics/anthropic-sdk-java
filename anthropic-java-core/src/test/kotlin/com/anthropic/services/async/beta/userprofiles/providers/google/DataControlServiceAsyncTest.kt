package com.anthropic.services.async.beta.userprofiles.providers.google

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.BetaGoogleManagedCmekEnabledParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.BetaGoogleManagedStorageDisabledParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlRetrieveParams
import com.anthropic.models.beta.userprofiles.providers.google.datacontrols.DataControlUpdateParams
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
            client.beta().userProfiles().providers().google().dataControls().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaGoogleManagedDataControlsFuture =
            dataControlServiceAsync.retrieve(
                DataControlRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaGoogleManagedDataControls = betaGoogleManagedDataControlsFuture.get()
        betaGoogleManagedDataControls.validate()
    }

    @Test
    fun update() {
        val dataControlServiceAsync =
            client.beta().userProfiles().providers().google().dataControls().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaGoogleManagedDataControlsFuture =
            dataControlServiceAsync.update(
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

        val betaGoogleManagedDataControls = betaGoogleManagedDataControlsFuture.get()
        betaGoogleManagedDataControls.validate()
    }
}
