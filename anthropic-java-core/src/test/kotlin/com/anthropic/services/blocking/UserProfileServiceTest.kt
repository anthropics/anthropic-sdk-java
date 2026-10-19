package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.userprofiles.UserProfileAccessType
import com.anthropic.models.userprofiles.UserProfileCreateParams
import com.anthropic.models.userprofiles.UserProfileDeleteParams
import com.anthropic.models.userprofiles.UserProfileExternalUserAccountStatus
import com.anthropic.models.userprofiles.UserProfileExternalUserDetailsParams
import com.anthropic.models.userprofiles.UserProfileExternalUserEntityType
import com.anthropic.models.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.userprofiles.UserProfileUpdateParams
import java.time.OffsetDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserProfileServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val userProfileService = client.userProfiles()

        val userProfile =
            userProfileService.create(
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
            )

        userProfile.validate()
    }

    @Test
    fun retrieve() {
        val userProfileService = client.userProfiles()

        val userProfile =
            userProfileService.retrieve(
                UserProfileRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        userProfile.validate()
    }

    @Test
    fun update() {
        val userProfileService = client.userProfiles()

        val userProfile =
            userProfileService.update(
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
            )

        userProfile.validate()
    }

    @Test
    fun list() {
        val userProfileService = client.userProfiles()

        val page = userProfileService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val userProfileService = client.userProfiles()

        val deletedUserProfile =
            userProfileService.delete(
                UserProfileDeleteParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        deletedUserProfile.validate()
    }
}
