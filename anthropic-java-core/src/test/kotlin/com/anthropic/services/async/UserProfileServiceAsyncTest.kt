package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
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
internal class UserProfileServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val userProfileServiceAsync = client.userProfiles()

        val userProfileFuture =
            userProfileServiceAsync.create(
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

        val userProfile = userProfileFuture.get()
        userProfile.validate()
    }

    @Test
    fun retrieve() {
        val userProfileServiceAsync = client.userProfiles()

        val userProfileFuture =
            userProfileServiceAsync.retrieve(
                UserProfileRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val userProfile = userProfileFuture.get()
        userProfile.validate()
    }

    @Test
    fun update() {
        val userProfileServiceAsync = client.userProfiles()

        val userProfileFuture =
            userProfileServiceAsync.update(
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

        val userProfile = userProfileFuture.get()
        userProfile.validate()
    }

    @Test
    fun list() {
        val userProfileServiceAsync = client.userProfiles()

        val pageFuture = userProfileServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun delete() {
        val userProfileServiceAsync = client.userProfiles()

        val deletedUserProfileFuture =
            userProfileServiceAsync.delete(
                UserProfileDeleteParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val deletedUserProfile = deletedUserProfileFuture.get()
        deletedUserProfile.validate()
    }
}
