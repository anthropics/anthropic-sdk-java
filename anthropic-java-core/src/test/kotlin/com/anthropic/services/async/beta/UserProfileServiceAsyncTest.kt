// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.JsonValue
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.userprofiles.UserProfileCreateEnrollmentUrlParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.userprofiles.UserProfileRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserProfileServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileServiceAsync = client.beta().userProfiles()

        val betaUserProfileFuture =
            userProfileServiceAsync.create(
                UserProfileCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .externalId("user_12345")
                    .metadata(UserProfileCreateParams.Metadata.builder().build())
                    .name("x")
                    .relationship(UserProfileCreateParams.Relationship.EXTERNAL)
                    .build()
            )

        val betaUserProfile = betaUserProfileFuture.get()
        betaUserProfile.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileServiceAsync = client.beta().userProfiles()

        val betaUserProfileFuture =
            userProfileServiceAsync.retrieve(
                UserProfileRetrieveParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaUserProfile = betaUserProfileFuture.get()
        betaUserProfile.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileServiceAsync = client.beta().userProfiles()

        val betaUserProfileFuture =
            userProfileServiceAsync.update(
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

        val betaUserProfile = betaUserProfileFuture.get()
        betaUserProfile.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileServiceAsync = client.beta().userProfiles()

        val pageFuture = userProfileServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun createEnrollmentUrl() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userProfileServiceAsync = client.beta().userProfiles()

        val betaUserProfileEnrollmentUrlFuture =
            userProfileServiceAsync.createEnrollmentUrl(
                UserProfileCreateEnrollmentUrlParams.builder()
                    .userProfileId("uprof_011CZkZCu8hGbp5mYRQgUmz9")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaUserProfileEnrollmentUrl = betaUserProfileEnrollmentUrlFuture.get()
        betaUserProfileEnrollmentUrl.validate()
    }
}
