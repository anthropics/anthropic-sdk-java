package com.anthropic.models.userprofiles

import com.anthropic.core.http.Headers
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserProfileCreateParamsTest {

    @Test
    fun create() {
        UserProfileCreateParams.builder()
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .accessType(UserProfileAccessType.APPLICATION)
            .externalUserDetails(
                UserProfileExternalUserDetailsParams.builder()
                    .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                    .country("US")
                    .emailHash("x")
                    .entityType(UserProfileExternalUserEntityType.BUSINESS)
                    .nameHash("x")
                    .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                    .referenceId("user_12345")
                    .build()
            )
            .metadata(UserProfileCreateParams.Metadata.builder().build())
            .name("Example User")
            .build()
    }

    @Test
    fun headers() {
        val params =
            UserProfileCreateParams.builder()
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .accessType(UserProfileAccessType.APPLICATION)
                .externalUserDetails(
                    UserProfileExternalUserDetailsParams.builder()
                        .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                        .country("US")
                        .emailHash("x")
                        .entityType(UserProfileExternalUserEntityType.BUSINESS)
                        .nameHash("x")
                        .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                        .referenceId("user_12345")
                        .build()
                )
                .metadata(UserProfileCreateParams.Metadata.builder().build())
                .name("Example User")
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
        val params = UserProfileCreateParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            UserProfileCreateParams.builder()
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .accessType(UserProfileAccessType.APPLICATION)
                .externalUserDetails(
                    UserProfileExternalUserDetailsParams.builder()
                        .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                        .country("US")
                        .emailHash("x")
                        .entityType(UserProfileExternalUserEntityType.BUSINESS)
                        .nameHash("x")
                        .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                        .referenceId("user_12345")
                        .build()
                )
                .metadata(UserProfileCreateParams.Metadata.builder().build())
                .name("Example User")
                .build()

        val body = params._body()

        assertThat(body.accessType()).contains(UserProfileAccessType.APPLICATION)
        assertThat(body.externalUserDetails())
            .contains(
                UserProfileExternalUserDetailsParams.builder()
                    .accountStatus(UserProfileExternalUserAccountStatus.ACTIVE)
                    .country("US")
                    .emailHash("x")
                    .entityType(UserProfileExternalUserEntityType.BUSINESS)
                    .nameHash("x")
                    .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                    .referenceId("user_12345")
                    .build()
            )
        assertThat(body.metadata()).contains(UserProfileCreateParams.Metadata.builder().build())
        assertThat(body.name()).contains("Example User")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = UserProfileCreateParams.builder().build()

        val body = params._body()
    }
}
