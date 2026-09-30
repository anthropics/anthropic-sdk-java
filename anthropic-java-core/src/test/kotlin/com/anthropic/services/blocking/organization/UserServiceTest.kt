package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.users.UserUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class UserServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userService = client.organization().users()

        val organizationUser = userService.retrieve("user_id")

        organizationUser.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userService = client.organization().users()

        val organizationUser =
            userService.update(
                UserUpdateParams.builder()
                    .userId("user_id")
                    .role(UserUpdateParams.Role.USER)
                    .build()
            )

        organizationUser.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userService = client.organization().users()

        val page = userService.list()

        page.response().validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val userService = client.organization().users()

        val user = userService.remove("user_id")

        user.validate()
    }
}
