package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalStorageActivateParamsTest {

    @Test
    fun create() {
        ExternalStorageActivateParams.builder()
            .externalStorageId("external_storage_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ExternalStorageActivateParams.builder().externalStorageId("external_storage_id").build()

        assertThat(params._pathParam(0)).isEqualTo("external_storage_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ExternalStorageActivateParams.builder()
                .externalStorageId("external_storage_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "efs-2026-10-01").joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            ExternalStorageActivateParams.builder().externalStorageId("external_storage_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
