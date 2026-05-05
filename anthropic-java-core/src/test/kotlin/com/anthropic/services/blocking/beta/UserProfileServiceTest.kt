// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserProfileServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileService = client.beta().userProfiles()

        val betaUserProfile =
            userProfileService.create(
                UserProfileCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .externalId("user_12345")
                    .metadata(UserProfileCreateParams.Metadata.builder().build())
                    .name("x")
                    .relationship(UserProfileCreateParams.Relationship.EXTERNAL)
                    .build()
            )

        betaUserProfile.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileService = client.beta().userProfiles()

        val betaUserProfile =
            userProfileService.retrieve(
                UserProfileRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaUserProfile.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileService = client.beta().userProfiles()

        val betaUserProfile =
            userProfileService.update(
                UserProfileUpdateParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .externalId("user_12345")
                    .metadata(
                        UserProfileUpdateParams.Metadata.builder()
                            .putAdditionalProperty("foo", JsonValue.from("string"))
                            .build()
                    )
                    .name("x")
                    .relationship(UserProfileUpdateParams.Relationship.EXTERNAL)
                    .build()
            )

        betaUserProfile.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileService = client.beta().userProfiles()

        val page = userProfileService.list()

        page.response().validate()
    }

    @Test
    fun createEnrollmentUrl() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileService = client.beta().userProfiles()

        val betaUserProfileEnrollmentUrl =
            userProfileService.createEnrollmentUrl(
                UserProfileCreateEnrollmentUrlParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaUserProfileEnrollmentUrl.validate()
    }
}
