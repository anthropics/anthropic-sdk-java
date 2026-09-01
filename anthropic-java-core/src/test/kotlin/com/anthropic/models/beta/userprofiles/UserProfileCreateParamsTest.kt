// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCreateParamsTest {

    @Test
    fun create() {
        UserProfileCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .accessType(UserProfileCreateParams.AccessType.APPLICATION)
            .externalId("user_12345")
            .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
            .metadata(UserProfileCreateParams.Metadata.builder().build())
            .name("x")
            .build()
    }

    @Test
    fun headers() {
        val params =
            UserProfileCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .accessType(UserProfileCreateParams.AccessType.APPLICATION)
                .externalId("user_12345")
                .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                .metadata(UserProfileCreateParams.Metadata.builder().build())
                .name("x")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = UserProfileCreateParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            UserProfileCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .accessType(UserProfileCreateParams.AccessType.APPLICATION)
                .externalId("user_12345")
                .externalUserOnboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                .metadata(UserProfileCreateParams.Metadata.builder().build())
                .name("x")
                .build()

        val body = params._body()

        assertThat(body.accessType()).contains(UserProfileCreateParams.AccessType.APPLICATION)
        assertThat(body.externalId()).contains("user_12345")
        assertThat(body.externalUserOnboardedAt())
            .contains(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
        assertThat(body.metadata()).contains(UserProfileCreateParams.Metadata.builder().build())
        assertThat(body.name()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = UserProfileCreateParams.builder().build()

        val body = params._body()
    }
}
