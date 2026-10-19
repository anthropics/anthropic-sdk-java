package com.anthropic.models.userprofiles

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileUpdateParamsTest {

    @Test
    fun create() {
        UserProfileUpdateParams.builder()
            .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .accessType(UserProfileAccessType.APPLICATION)
            .externalUserDetails(
                UserProfileExternalUserDetailsParams.builder()
                    .accountStatus(UserProfileExternalUserAccountStatus.SUSPENDED)
                    .country("country")
                    .emailHash("x")
                    .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                    .nameHash("x")
                    .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .referenceId("x")
                    .build()
            )
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
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .accessType(UserProfileAccessType.APPLICATION)
                .externalUserDetails(
                    UserProfileExternalUserDetailsParams.builder()
                        .accountStatus(UserProfileExternalUserAccountStatus.SUSPENDED)
                        .country("country")
                        .emailHash("x")
                        .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                        .nameHash("x")
                        .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .referenceId("x")
                        .build()
                )
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
                Headers.builder()
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
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
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .accessType(UserProfileAccessType.APPLICATION)
                .externalUserDetails(
                    UserProfileExternalUserDetailsParams.builder()
                        .accountStatus(UserProfileExternalUserAccountStatus.SUSPENDED)
                        .country("country")
                        .emailHash("x")
                        .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                        .nameHash("x")
                        .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .referenceId("x")
                        .build()
                )
                .metadata(
                    UserProfileUpdateParams.Metadata.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .name("x")
                .build()

        val body = params._body()

        assertThat(body.accessType()).contains(UserProfileAccessType.APPLICATION)
        assertThat(body.externalUserDetails())
            .contains(
                UserProfileExternalUserDetailsParams.builder()
                    .accountStatus(UserProfileExternalUserAccountStatus.SUSPENDED)
                    .country("country")
                    .emailHash("x")
                    .entityType(UserProfileExternalUserEntityType.INDIVIDUAL)
                    .nameHash("x")
                    .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .referenceId("x")
                    .build()
            )
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
