// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.skills.versions

import com.anthropic.core.http.Headers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionRetrieveParamsTest {

    @Test
    fun create() {
        VersionRetrieveParams.builder()
            .skillId("skill_id")
            .version("version")
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = VersionRetrieveParams.builder().skillId("skill_id").version("version").build()

        assertThat(params._pathParam(0)).isEqualTo("skill_id")
        assertThat(params._pathParam(1)).isEqualTo("version")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VersionRetrieveParams.builder()
                .skillId("skill_id")
                .version("version")
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
        val params = VersionRetrieveParams.builder().skillId("skill_id").version("version").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
