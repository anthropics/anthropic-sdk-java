// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceCreateParamsTest {

    @Test
    fun create() {
        WorkspaceCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .name("x")
            .dataResidency(
                BetaDataResidencyCreateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                    .build()
            )
            .displayColor("#6C5BB9")
            .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
            .tags(
                WorkspaceCreateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
            .build()
    }

    @Test
    fun headers() {
        val params =
            WorkspaceCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .name("x")
                .dataResidency(
                    BetaDataResidencyCreateConfig.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(
                            BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL
                        )
                        .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .tags(
                    WorkspaceCreateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = WorkspaceCreateParams.builder().name("x").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            WorkspaceCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .name("x")
                .dataResidency(
                    BetaDataResidencyCreateConfig.builder()
                        .allowedInferenceGeosUnrestricted()
                        .defaultInferenceGeo(
                            BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL
                        )
                        .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                        .build()
                )
                .displayColor("#6C5BB9")
                .externalKeyId("ekey_01SDCCSbTxrXDpWc1phhtcfK")
                .tags(
                    WorkspaceCreateParams.Tags.builder()
                        .putAdditionalProperty("env", JsonValue.from("prod"))
                        .putAdditionalProperty("team", JsonValue.from("platform"))
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("x")
        assertThat(body.dataResidency())
            .contains(
                BetaDataResidencyCreateConfig.builder()
                    .allowedInferenceGeosUnrestricted()
                    .defaultInferenceGeo(BetaDataResidencyCreateConfig.DefaultInferenceGeo.GLOBAL)
                    .workspaceGeo(BetaDataResidencyCreateConfig.WorkspaceGeo.US)
                    .build()
            )
        assertThat(body.displayColor()).contains("#6C5BB9")
        assertThat(body.externalKeyId()).contains("ekey_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(body.tags())
            .contains(
                WorkspaceCreateParams.Tags.builder()
                    .putAdditionalProperty("env", JsonValue.from("prod"))
                    .putAdditionalProperty("team", JsonValue.from("platform"))
                    .build()
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = WorkspaceCreateParams.builder().name("x").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("x")
    }
}
