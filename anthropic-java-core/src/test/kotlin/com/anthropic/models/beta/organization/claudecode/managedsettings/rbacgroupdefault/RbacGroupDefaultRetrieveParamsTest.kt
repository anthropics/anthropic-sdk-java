package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupDefaultRetrieveParamsTest {

    @Test
    fun create() {
        RbacGroupDefaultRetrieveParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .build()
    }

    @Test
    fun headers() {
        val params =
            RbacGroupDefaultRetrieveParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf(
                                "computer-use-2024-10-22",
                                "ce-claude-code-managed-settings-2026-10-06",
                            )
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = RbacGroupDefaultRetrieveParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
