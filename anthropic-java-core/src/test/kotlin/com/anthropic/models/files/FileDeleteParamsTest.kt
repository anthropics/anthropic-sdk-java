// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.files

import com.anthropic.core.http.Headers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileDeleteParamsTest {

    @Test
    fun create() {
        FileDeleteParams.builder()
            .fileId("file_id")
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = FileDeleteParams.builder().fileId("file_id").build()

        assertThat(params._pathParam(0)).isEqualTo("file_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            FileDeleteParams.builder()
                .fileId("file_id")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = FileDeleteParams.builder().fileId("file_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
