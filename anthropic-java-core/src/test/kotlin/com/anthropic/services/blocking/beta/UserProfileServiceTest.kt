package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.BetaUserProfileExternalUserDetailsParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileDeleteParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
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
        val userProfileService = client.beta().userProfiles()

        val betaUserProfile =
            userProfileService.create(
                UserProfileCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .accessType(UserProfileCreateParams.AccessType.APPLICATION)
                    .externalId("x")
                    .externalUserDetails(
                        BetaUserProfileExternalUserDetailsParams.builder()
                            .accountStatus(
                                BetaUserProfileExternalUserDetailsParams.AccountStatus.ACTIVE
                            )
                            .country("US")
                            .emailHash("x")
                            .entityType(
                                BetaUserProfileExternalUserDetailsParams.EntityType.BUSINESS
                            )
                            .nameHash("x")
                            .onboardedAt(OffsetDateTime.parse("2024-11-02T08:15:00Z"))
                            .referenceId("user_12345")
                            .build()
                    )
                    .externalUserOnboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .metadata(UserProfileCreateParams.Metadata.builder().build())
                    .name("Example User")
                    .build()
            )

        betaUserProfile.validate()
    }

    @Test
    fun retrieve() {
        val userProfileService = client.beta().userProfiles()

        val betaUserProfile =
            userProfileService.retrieve(
                UserProfileRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaUserProfile.validate()
    }

    @Test
    fun update() {
        val userProfileService = client.beta().userProfiles()

        val betaUserProfile =
            userProfileService.update(
                UserProfileUpdateParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .accessType(UserProfileUpdateParams.AccessType.APPLICATION)
                    .externalId("x")
                    .externalUserDetails(
                        BetaUserProfileExternalUserDetailsParams.builder()
                            .accountStatus(
                                BetaUserProfileExternalUserDetailsParams.AccountStatus.SUSPENDED
                            )
                            .country("country")
                            .emailHash("x")
                            .entityType(
                                BetaUserProfileExternalUserDetailsParams.EntityType.INDIVIDUAL
                            )
                            .nameHash("x")
                            .onboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                            .referenceId("x")
                            .build()
                    )
                    .externalUserOnboardedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .metadata(
                        UserProfileUpdateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("x")
                    .build()
            )

        betaUserProfile.validate()
    }

    @Test
    fun list() {
        val userProfileService = client.beta().userProfiles()

        val page = userProfileService.list()

        page.response().validate()
    }

    @Test
    fun delete() {
        val userProfileService = client.beta().userProfiles()

        val betaDeletedUserProfile =
            userProfileService.delete(
                UserProfileDeleteParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaDeletedUserProfile.validate()
    }

    @Test
    fun createEnrollmentUrl() {
        val userProfileService = client.beta().userProfiles()

        val betaUserProfileEnrollmentUrl =
            userProfileService.createEnrollmentUrl(
                UserProfileCreateEnrollmentUrlParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaUserProfileEnrollmentUrl.validate()
    }
}
