package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupSetParamsTest {

    @Test
    fun create() {
        RbacGroupSetParams.builder()
            .rbacGroupId("rbac_group_id")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .settings(
                RbacGroupSetParams.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            RbacGroupSetParams.builder()
                .rbacGroupId("rbac_group_id")
                .settings(
                    RbacGroupSetParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_group_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            RbacGroupSetParams.builder()
                .rbacGroupId("rbac_group_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .settings(
                    RbacGroupSetParams.Settings.builder()
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
            RbacGroupSetParams.builder()
                .rbacGroupId("rbac_group_id")
                .settings(
                    RbacGroupSetParams.Settings.builder()
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
            RbacGroupSetParams.builder()
                .rbacGroupId("rbac_group_id")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .settings(
                    RbacGroupSetParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.settings())
            .isEqualTo(
                RbacGroupSetParams.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            RbacGroupSetParams.builder()
                .rbacGroupId("rbac_group_id")
                .settings(
                    RbacGroupSetParams.Settings.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.settings())
            .isEqualTo(
                RbacGroupSetParams.Settings.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
    }
}
