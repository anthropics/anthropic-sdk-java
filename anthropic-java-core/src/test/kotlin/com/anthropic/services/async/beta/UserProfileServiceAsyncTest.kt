package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class UserProfileServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val userProfileServiceAsync =
            client.beta().userProfiles().withOptions { it.putQueryParam("beta", "true") }

        val betaUserProfileFuture =
            userProfileServiceAsync.create(
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

        val betaUserProfile = betaUserProfileFuture.get()
        betaUserProfile.validate()
    }

    @Test
    fun retrieve() {
        val userProfileServiceAsync =
            client.beta().userProfiles().withOptions { it.putQueryParam("beta", "true") }

        val betaUserProfileFuture =
            userProfileServiceAsync.retrieve(
                UserProfileRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaUserProfile = betaUserProfileFuture.get()
        betaUserProfile.validate()
    }

    @Test
    fun update() {
        val userProfileServiceAsync =
            client.beta().userProfiles().withOptions { it.putQueryParam("beta", "true") }

        val betaUserProfileFuture =
            userProfileServiceAsync.update(
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

        val betaUserProfile = betaUserProfileFuture.get()
        betaUserProfile.validate()
    }

    @Test
    fun list() {
        val userProfileServiceAsync =
            client.beta().userProfiles().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = userProfileServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val userProfileServiceAsync =
            client.beta().userProfiles().withOptions { it.putQueryParam("beta", "true") }

        val betaDeletedUserProfileFuture =
            userProfileServiceAsync.delete(
                UserProfileDeleteParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaDeletedUserProfile = betaDeletedUserProfileFuture.get()
        betaDeletedUserProfile.validate()
    }

    @Test
    fun createEnrollmentUrl() {
        val userProfileServiceAsync =
            client.beta().userProfiles().withOptions { it.putQueryParam("beta", "true") }

        val betaUserProfileEnrollmentUrlFuture =
            userProfileServiceAsync.createEnrollmentUrl(
                UserProfileCreateEnrollmentUrlParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaUserProfileEnrollmentUrl = betaUserProfileEnrollmentUrlFuture.get()
        betaUserProfileEnrollmentUrl.validate()
    }
}
