package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroupdefault

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupDefaultUpdateParamsTest {

    @Test
    fun create() {
        RbacGroupDefaultUpdateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .settings(
                RbacGroupDefaultUpdateParams.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
            .build()
    }

    @Test
    fun headers() {
        val params =
            RbacGroupDefaultUpdateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .settings(
                    RbacGroupDefaultUpdateParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
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
        val params =
            RbacGroupDefaultUpdateParams.builder()
                .settings(
                    RbacGroupDefaultUpdateParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            RbacGroupDefaultUpdateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .settings(
                    RbacGroupDefaultUpdateParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.settings())
            .contains(
                RbacGroupDefaultUpdateParams.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            RbacGroupDefaultUpdateParams.builder()
                .settings(
                    RbacGroupDefaultUpdateParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.settings())
            .contains(
                RbacGroupDefaultUpdateParams.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }
}
