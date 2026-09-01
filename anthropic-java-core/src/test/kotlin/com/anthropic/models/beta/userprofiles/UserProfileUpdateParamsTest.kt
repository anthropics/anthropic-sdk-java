// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileUpdateParamsTest {

    @Test
    fun create() {
        UserProfileUpdateParams.builder()
            .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .accessType(UserProfileUpdateParams.AccessType.APPLICATION)
            .externalId("user_12345")
            .externalUserOnboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
            .metadata(
                UserProfileUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
            .name("x")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            UserProfileUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("uprof_011CZkZCu8hGbp5mYRQgUmz9")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            UserProfileUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .accessType(UserProfileUpdateParams.AccessType.APPLICATION)
                .externalId("user_12345")
                .externalUserOnboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .metadata(
                    UserProfileUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
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
        val params =
            UserProfileUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            UserProfileUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .accessType(UserProfileUpdateParams.AccessType.APPLICATION)
                .externalId("user_12345")
                .externalUserOnboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .metadata(
                    UserProfileUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .name("x")
                .build()

        val body = params._body()

        assertThat(body.accessType()).contains(UserProfileUpdateParams.AccessType.APPLICATION)
        assertThat(body.externalId()).contains("user_12345")
        assertThat(body.externalUserOnboardedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(body.metadata())
            .contains(
                UserProfileUpdateParams.Metadata.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(body.name()).contains("x")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            UserProfileUpdateParams.builder()
                .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                .build()

        val body = params._body()
    }
}
