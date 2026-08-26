// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.organization.users.UserUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userServiceAsync = client.beta().organization().users()

        val betaOrganizationUserFuture = userServiceAsync.retrieve("user_id")

        val betaOrganizationUser = betaOrganizationUserFuture.get()
        betaOrganizationUser.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userServiceAsync = client.beta().organization().users()

        val betaOrganizationUserFuture =
            userServiceAsync.update(
                UserUpdateParams.builder()
                    .userId("user_id")
                    .role(UserUpdateParams.Role.USER)
                    .build()
            )

        val betaOrganizationUser = betaOrganizationUserFuture.get()
        betaOrganizationUser.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userServiceAsync = client.beta().organization().users()

        val pageFuture = userServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userServiceAsync = client.beta().organization().users()

        val userFuture = userServiceAsync.remove("user_id")

        val user = userFuture.get()
        user.validate()
    }
}
