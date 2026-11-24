package com.anthropic.models.beta.organization.providers.google.projects.datacontrols

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DataControlUpdateParamsTest {

    @Test
    fun create() {
        DataControlUpdateParams.builder()
            .projectNumber("123456789012")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .cmek(BetaGoogleManagedProjectCmekDisabledParams.builder().build())
            .storage(BetaGoogleManagedProjectStorageDisabledParams.builder().build())
            .build()
    }

    @Test
    fun pathParams() {
        val params = DataControlUpdateParams.builder().projectNumber("123456789012").build()

        assertThat(params._pathParam(0)).isEqualTo("123456789012")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            DataControlUpdateParams.builder()
                .projectNumber("123456789012")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .cmek(BetaGoogleManagedProjectCmekDisabledParams.builder().build())
                .storage(BetaGoogleManagedProjectStorageDisabledParams.builder().build())
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "google-managed-data-controls-2026-09-01")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = DataControlUpdateParams.builder().projectNumber("123456789012").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            DataControlUpdateParams.builder()
                .projectNumber("123456789012")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .cmek(BetaGoogleManagedProjectCmekDisabledParams.builder().build())
                .storage(BetaGoogleManagedProjectStorageDisabledParams.builder().build())
                .build()

        val body = params._body()

        assertThat(body.cmek())
            .contains(
                BetaGoogleManagedProjectCmekControlParams.ofDisabled(
                    BetaGoogleManagedProjectCmekDisabledParams.builder().build()
                )
            )
        assertThat(body.storage())
            .contains(
                BetaGoogleManagedProjectStorageControlParams.ofDisabled(
                    BetaGoogleManagedProjectStorageDisabledParams.builder().build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = DataControlUpdateParams.builder().projectNumber("123456789012").build()

        val body = params._body()
    }
}
