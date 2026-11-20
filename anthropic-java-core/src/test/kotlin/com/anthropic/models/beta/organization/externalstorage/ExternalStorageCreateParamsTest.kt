package com.anthropic.models.beta.organization.externalstorage

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalStorageCreateParamsTest {

    @Test
    fun create() {
        ExternalStorageCreateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .providerConfig(
                BetaAwsExternalStorageConfigParam.builder()
                    .accountId("111122223333")
                    .bucketName("acme-safety-retention")
                    .region("us-east-2")
                    .build()
            )
            .displayName("x")
            .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
            .build()
    }

    @Test
    fun headers() {
        val params =
            ExternalStorageCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .providerConfig(
                    BetaAwsExternalStorageConfigParam.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
                .displayName("x")
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
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
            ExternalStorageCreateParams.builder()
                .providerConfig(
                    BetaAwsExternalStorageConfigParam.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ExternalStorageCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .providerConfig(
                    BetaAwsExternalStorageConfigParam.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
                .displayName("x")
                .userProfileId("uprof_01SDCCSbTxrXDpWc1phhtcfK")
                .build()

        val body = params._body()

        assertThat(body.providerConfig())
            .isEqualTo(
                ExternalStorageCreateParams.ProviderConfig.ofAws(
                    BetaAwsExternalStorageConfigParam.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
            )
        assertThat(body.displayName()).contains("x")
        assertThat(body.userProfileId()).contains("uprof_01SDCCSbTxrXDpWc1phhtcfK")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ExternalStorageCreateParams.builder()
                .providerConfig(
                    BetaAwsExternalStorageConfigParam.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
                .build()

        val body = params._body()

        assertThat(body.providerConfig())
            .isEqualTo(
                ExternalStorageCreateParams.ProviderConfig.ofAws(
                    BetaAwsExternalStorageConfigParam.builder()
                        .accountId("111122223333")
                        .bucketName("acme-safety-retention")
                        .region("us-east-2")
                        .build()
                )
            )
    }
}
