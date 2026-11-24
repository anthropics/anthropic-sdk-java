package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DataControlUpdateParamsTest {

    @Test
    fun create() {
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
    }

    @Test
    fun pathParams() {
        val params =
            DataControlUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
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

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf(
                                "computer-use-2024-10-22",
                                "user-profiles-2026-09-04",
                                "google-managed-data-controls-2026-09-01",
                            )
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            DataControlUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
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

        val body = params._body()

        assertThat(body.cmek())
            .contains(
                BetaGoogleManagedCmekControlParams.ofEnabled(
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
            )
        assertThat(body.storage())
            .contains(
                BetaGoogleManagedStorageControlParams.ofDisabled(
                    BetaGoogleManagedStorageDisabledParams.builder().build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            DataControlUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        val body = params._body()
    }
}
