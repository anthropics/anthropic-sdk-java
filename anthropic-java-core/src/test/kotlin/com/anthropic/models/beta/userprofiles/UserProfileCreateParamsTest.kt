// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCreateParamsTest {

    @Test
    fun create() {
        UserProfileCreateParams.builder()
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .externalId("user_12345")
            .metadata(UserProfileCreateParams.Metadata.builder().build())
            .name("x")
            .relationship(UserProfileCreateParams.Relationship.EXTERNAL)
            .build()
    }

    @Test
    fun headers() {
        val params =
            UserProfileCreateParams.builder()
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .externalId("user_12345")
                .metadata(UserProfileCreateParams.Metadata.builder().build())
                .name("x")
                .relationship(UserProfileCreateParams.Relationship.EXTERNAL)
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
                .externalId("user_12345")
                .metadata(UserProfileCreateParams.Metadata.builder().build())
                .name("x")
                .relationship(UserProfileCreateParams.Relationship.EXTERNAL)
                .build()

        val body = params._body()

        assertThat(body.externalId()).contains("user_12345")
        assertThat(body.metadata()).contains(UserProfileCreateParams.Metadata.builder().build())
        assertThat(body.name()).contains("x")
        assertThat(body.relationship()).contains(UserProfileCreateParams.Relationship.EXTERNAL)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = UserProfileCreateParams.builder().build()

        val body = params._body()
    }
}
